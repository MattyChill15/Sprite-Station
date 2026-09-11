package com.example.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CrewMember
import com.example.model.CrewRoster
import com.example.model.OpsFeedData
import com.example.model.OpsPaneType
import com.example.ui.components.DutyPad
import com.example.ui.components.HolotableMux
import com.example.ui.components.MockBanner
import com.example.ui.components.PixelSprite
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

/**
 * Floor Screen (Home)
 *
 * Dark isometric command-center CIC floor.
 * - Hot-10 crew on fixed duty pads around central cyan holotable (5x2 lattice).
 * - Chief patrols aisle on floor tiles with custom walk cycle and flipX for west.
 * - Stark-style cyan glass holotable with etched ops panes.
 * - Responsive cover-mapping for Samsung Galaxy Fold 7 (tall folded vs wide unfolded).
 * - Draw order by Y for authentic depth.
 * - Tap any sprite/duty pad -> open Chamber / Tactical Dossier.
 */
@Composable
fun FloorScreen(
    onSelectCrew: (CrewMember) -> Unit,
    modifier: Modifier = Modifier
) {
    var opsFeed by remember { mutableStateOf(OpsFeedData()) }
    var pulsingAgentId by remember { mutableStateOf<String?>("shorty") }

    // Auto-pulse duty pad when pane changes or is focused
    fun triggerPaneFocus(pane: OpsPaneType) {
        opsFeed = opsFeed.copy(selectedPane = pane)
        pulsingAgentId = pane.primaryAgentId
    }

    // Clear pulse after ~700ms
    LaunchedEffect(pulsingAgentId) {
        if (pulsingAgentId != null) {
            delay(750)
            pulsingAgentId = null
        }
    }

    // 10 crew roster mapped to 5x2 lattice
    // Top Row (5 agents): Shayde, Gaps, Shorty, Dodge, Wiz
    val topRow = remember {
        listOf(
            CrewRoster.findById("shayde"),
            CrewRoster.findById("gaps"),
            CrewRoster.findById("shorty"),
            CrewRoster.findById("dodge"),
            CrewRoster.findById("wiz")
        )
    }

    // Bottom Row (5 agents): Rogue, Affleck, Chief, Cudi, Snoop
    val bottomRow = remember {
        listOf(
            CrewRoster.findById("rogue"),
            CrewRoster.findById("affleck"),
            CrewRoster.findById("chief"),
            CrewRoster.findById("cudi"),
            CrewRoster.findById("snoop")
        )
    }

    // Chief Floor Patrol Engine
    // Patrols aisle floor corridor smoothly back and forth
    val chiefMember = remember { CrewRoster.findById("chief") }
    var chiefWalkXPercent by remember { mutableStateOf(0.15f) }
    var chiefMovingEast by remember { mutableStateOf(true) }
    var chiefWalkFrame by remember { mutableIntStateOf(0) }
    var chiefIsPaused by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            if (chiefIsPaused) {
                // Brief idle_south pause at aisle end
                delay(1200)
                chiefIsPaused = false
                chiefMovingEast = !chiefMovingEast
            } else {
                val step = 0.015f
                if (chiefMovingEast) {
                    chiefWalkXPercent += step
                    if (chiefWalkXPercent >= 0.82f) {
                        chiefWalkXPercent = 0.82f
                        chiefIsPaused = true
                    }
                } else {
                    chiefWalkXPercent -= step
                    if (chiefWalkXPercent <= 0.15f) {
                        chiefWalkXPercent = 0.15f
                        chiefIsPaused = true
                    }
                }

                chiefWalkFrame = (chiefWalkFrame + 1) % 8
                // Walk timing: frames 3 and 7 hold longer (~180ms); others ~90ms
                val holdMs = if (chiefWalkFrame == 3 || chiefWalkFrame == 7) 180L else 90L
                delay(holdMs)
            }
        }
    }

    // Floor Plate Root
    BoxWithConstraints(
        modifier = modifier
            .testTag("floor_screen_root")
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF070B11),
                        Color(0xFF0B121A),
                        Color(0xFF060A0E)
                    )
                )
            )
            .drawBehind {
                val w = size.width
                val h = size.height

                // Horizon & Vanishing Point for Isometric Depth
                val horizonY = h * 0.11f
                val vpX = w * 0.5f
                val vpY = -h * 0.05f

                // Bulkhead wall background (above horizon)
                drawRect(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF03070B), Color(0xFF070E17)),
                        startY = 0f,
                        endY = horizonY
                    ),
                    topLeft = Offset.Zero,
                    size = androidx.compose.ui.geometry.Size(w, horizonY)
                )

                // Holographic world map / tactical blueprint grid lines on wall
                val wallLineCol = Color(0x1500E5FF)
                for (step in 1..4) {
                    val wy = horizonY * (step / 5f)
                    drawLine(wallLineCol, Offset(0f, wy), Offset(w, wy), strokeWidth = 1f)
                }
                drawLine(Color(0x3300E5FF), Offset(0f, horizonY), Offset(w, horizonY), strokeWidth = 2f)

                // Isometric Floor Perspective Rays radiating from vanishing point
                val floorRayCol = Color(0x1800E5FF)
                val rayCount = 12
                for (i in -rayCount..rayCount) {
                    val bottomX = vpX + (i.toFloat() / rayCount) * (w * 0.9f)
                    drawLine(floorRayCol, Offset(vpX + i * (w * 0.04f), horizonY), Offset(bottomX, h), strokeWidth = 1.2f)
                }

                // Foreshortened Transverse Floor Plates (narrower near horizon, wider in foreground)
                val plateCount = 10
                for (i in 1..plateCount) {
                    val progress = Math.pow(i.toDouble() / plateCount, 1.45).toFloat()
                    val py = horizonY + (h - horizonY) * progress
                    val alpha = (0x0C + (0x1C * progress).toInt()).coerceIn(0x0C, 0x2A)
                    drawLine(Color(0x00E5FF or (alpha shl 24)), Offset(0f, py), Offset(w, py), strokeWidth = 1.2f)
                }

                // Glowing Cyan Sub-floor Power Conduits converging towards Central Holotable
                val conduitCol = Color(0x3300E5FF)
                drawLine(conduitCol, Offset(w * 0.08f, h), Offset(w * 0.32f, h * 0.48f), strokeWidth = 2.5f)
                drawLine(conduitCol, Offset(w * 0.92f, h), Offset(w * 0.68f, h * 0.48f), strokeWidth = 2.5f)
                drawLine(conduitCol, Offset(w * 0.20f, horizonY), Offset(w * 0.35f, h * 0.42f), strokeWidth = 2f)
                drawLine(conduitCol, Offset(w * 0.80f, horizonY), Offset(w * 0.65f, h * 0.42f), strokeWidth = 2f)

                // Central Holotable Pedestal Glow Ring (beneath HolotableMux)
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(Color(0x2800E5FF), Color(0x0A00E5FF), Color(0x00000000)),
                        center = Offset(w * 0.5f, h * 0.45f),
                        radius = w * 0.45f
                    ),
                    center = Offset(w * 0.5f, h * 0.45f),
                    radius = w * 0.45f
                )

                // Caution hazard striping on outer corridor boundaries
                val stripeCol = Color(0x18FFB300)
                drawLine(stripeCol, Offset(0f, h - 72.dp.toPx()), Offset(w, h - 72.dp.toPx()), strokeWidth = 2f)
            }
            .padding(horizontal = 8.dp)
    ) {
        val screenWidth = maxWidth
        val isFoldedNarrow = screenWidth < 420.dp

        // Dynamic responsive sizing for 5 pads lattice and holotable
        val padWidth = if (isFoldedNarrow) 58.dp else 68.dp
        val spriteSize = if (isFoldedNarrow) 70.dp else 84.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 680.dp)
                .align(Alignment.Center)
        ) {
            Spacer(modifier = Modifier.height(6.dp))

            // Persistent MOCK Ops Status Banner
            MockBanner(modifier = Modifier.padding(horizontal = 4.dp))

            Spacer(modifier = Modifier.height(6.dp))

            // Station CIC Header Bar
            StationHeaderBar(
                selectedPane = opsFeed.selectedPane,
                onHeaderClick = {
                    onSelectCrew(chiefMember)
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // -------------------------------------------------------------
            // ROW 1: UPPER DUTY PADS (5 Agents)
            // Shayde | Gaps | Shorty | Dodge | Wiz
            // -------------------------------------------------------------
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                topRow.forEach { member ->
                    val isPulsing = pulsingAgentId == member.id ||
                            (opsFeed.selectedPane.name == member.relatedOpsPane && pulsingAgentId != null)

                    DutyPad(
                        crew = member,
                        isPulsing = isPulsing,
                        onSelect = onSelectCrew,
                        padWidth = padWidth,
                        spriteSize = spriteSize,
                        ambientCyanGlow = true
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // -------------------------------------------------------------
            // CENTER: STARK-STYLE CYAN HOLOTABLE OPS MULTIPLEXER
            // Feed lives IN the glass surface; responsive cover-mapping
            // -------------------------------------------------------------
            HolotableMux(
                opsFeed = opsFeed,
                onCyclePane = {
                    triggerPaneFocus(opsFeed.selectedPane.next())
                },
                onSelectPane = { selected ->
                    triggerPaneFocus(selected)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // -------------------------------------------------------------
            // PATROL CORRIDOR / AISLE (Floor Tiles, NOT on glass)
            // Chief patrols smoothly across with authentic walk cycle & flipX
            // -------------------------------------------------------------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(84.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0x180A1520))
                    .border(1.dp, Color(0x2200E5FF), RoundedCornerShape(6.dp))
                    .drawBehind {
                        // Corridor floor hatch pattern
                        val step = 16.dp.toPx()
                        var cx = 0f
                        while (cx <= size.width) {
                            drawLine(Color(0x0C00E5FF), Offset(cx, 0f), Offset(cx + 8f, size.height), 1f)
                            cx += step
                        }
                    }
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { onSelectCrew(chiefMember) }
                    ),
                contentAlignment = Alignment.CenterStart
            ) {
                // Aisle label watermark
                Text(
                    text = "◀ AISLE CORRIDOR // CHIEF PATROL SECTOR ▶",
                    color = Color(0x2500E5FF),
                    fontSize = 8.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )

                // Chief Patrolling Sprite (Feet planted on floor tiles, never on glass)
                val chiefPosX = (chiefWalkXPercent * (this@BoxWithConstraints.constraints.maxWidth - 200)).coerceAtLeast(0f)

                Box(
                    modifier = Modifier
                        .offset { IntOffset(chiefPosX.roundToInt(), 0) }
                        .padding(bottom = 2.dp)
                ) {
                    PixelSprite(
                        crew = chiefMember,
                        size = 78.dp,
                        walkFrame = chiefWalkFrame,
                        isWalking = !chiefIsPaused,
                        flipX = !chiefMovingEast,
                        ambientCyanGlow = true
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // -------------------------------------------------------------
            // ROW 2: LOWER DUTY PADS (5 Agents)
            // Rogue | Affleck | Chief (Pad Standby) | Cudi | Snoop
            // -------------------------------------------------------------
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                bottomRow.forEach { member ->
                    val isPulsing = pulsingAgentId == member.id ||
                            (opsFeed.selectedPane.name == member.relatedOpsPane && pulsingAgentId != null)

                    DutyPad(
                        crew = member,
                        isPulsing = isPulsing,
                        onSelect = onSelectCrew,
                        padWidth = padWidth,
                        spriteSize = spriteSize,
                        isChiefPatrolling = member.id == "chief" && !chiefIsPaused,
                        ambientCyanGlow = true
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Bottom Quick Info Strip: Tap hint & status indicator
            FloorFooterStrip(
                activePane = opsFeed.selectedPane,
                onQuickChief = { onSelectCrew(chiefMember) }
            )
        }
    }
}

@Composable
private fun StationHeaderBar(
    selectedPane: OpsPaneType,
    onHeaderClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .testTag("station_header_bar")
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF0C141C))
            .border(1.dp, Color(0x3300E5FF), RoundedCornerShape(8.dp))
            .clickable(onClick = onHeaderClick)
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFF132A54))
                        .border(1.dp, Color(0xFF00E5FF), RoundedCornerShape(4.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Security,
                        contentDescription = "Station Crest",
                        tint = Color(0xFF00E5FF),
                        modifier = Modifier.size(16.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "SPRITE STATION",
                            color = Color(0xFFFFFFFF),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Monospace,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(2.dp))
                                .background(Color(0x3300E5FF))
                                .padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Text(
                                text = "10 CREW ONLINE",
                                color = Color(0xFF00E5FF),
                                fontSize = 7.5.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                    Text(
                        text = "TOP-DOWN OPS FLOOR // APEX COMMAND CENTER",
                        color = Color(0x9994A3B8),
                        fontSize = 7.5.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            // Right side: Active Pane Callout
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0x2200E5FF))
                        .border(1.dp, Color(0x4400E5FF), RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "MUX: ${selectedPane.name}",
                        color = Color(0xFF00E5FF),
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }
    }
}

@Composable
private fun FloorFooterStrip(
    activePane: OpsPaneType,
    onQuickChief: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = Color(0x8000E5FF),
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "TAP SPRITE → TACTICAL DOSSIER",
                color = Color(0x9994A3B8),
                fontSize = 8.sp,
                fontFamily = FontFamily.Monospace
            )
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0x22132A54))
                .border(1.dp, Color(0x4400E5FF), RoundedCornerShape(4.dp))
                .clickable(onClick = onQuickChief)
                .padding(horizontal = 8.dp, vertical = 3.dp)
        ) {
            Text(
                text = "CHIEF UPLINK: t.me/Mindbangerbot",
                color = Color(0xFF00E5FF),
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}
