package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.OpsFeedData
import com.example.model.OpsPaneType

/**
 * Stark-Style Cyan Glass Holotable Ops Multiplexer
 *
 * Feed is etched directly into the cyan holographic glass plate.
 * Features:
 * - 4-terminal etched grid (SHORTS | ETSY | SPORTS | BURN)
 * - Tap cycles active focus with animated luminous cyan rim
 * - Marquee crawl with MOCK CTQ metrics
 * - Mild perspective tilt (rotateX = 8f) to read as holographic table glass
 * - Responsive cover mapping: sticks to table boundary on fold narrow and wide
 */
@Composable
fun HolotableMux(
    opsFeed: OpsFeedData,
    onCyclePane: () -> Unit,
    onSelectPane: (OpsPaneType) -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }

    // Scanline / Hologram shimmer
    val infiniteTransition = rememberInfiniteTransition(label = "holotable_shimmer")
    val shimmerAlpha by infiniteTransition.animateFloat(
        initialValue = 0.15f,
        targetValue = 0.35f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shimmerAlpha"
    )

    val gridSweep by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "gridSweep"
    )

    Box(
        modifier = modifier
            .testTag("holotable_multiplexer")
            .graphicsLayer {
                // Mild perspective tilt so feed lives IN the glass surface
                rotationX = 6f
                cameraDistance = 16f * density
            }
            .clip(RoundedCornerShape(12.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xE6051520),
                        Color(0xF0030C12),
                        Color(0xE6051520)
                    )
                )
            )
            .border(
                width = 1.5.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF00E5FF),
                        Color(0x6600E5FF),
                        Color(0xFF00B0FF)
                    )
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .drawBehind {
                // Cyan glass etched grid lines
                val w = size.width
                val h = size.height
                val gridColor = Color(0x1800E5FF)

                // Vertical table crosshairs
                drawLine(gridColor, Offset(w * 0.5f, 0f), Offset(w * 0.5f, h), strokeWidth = 1f)
                drawLine(gridColor, Offset(w * 0.25f, 0f), Offset(w * 0.25f, h), strokeWidth = 0.5f)
                drawLine(gridColor, Offset(w * 0.75f, 0f), Offset(w * 0.75f, h), strokeWidth = 0.5f)

                // Horizontal table crosshairs
                drawLine(gridColor, Offset(0f, h * 0.5f), Offset(w, h * 0.5f), strokeWidth = 1f)
                drawLine(gridColor, Offset(0f, h * 0.25f), Offset(w, h * 0.25f), strokeWidth = 0.5f)
                drawLine(gridColor, Offset(0f, h * 0.75f), Offset(w, h * 0.75f), strokeWidth = 0.5f)

                // Corner bracket etches
                val bSize = 12.dp.toPx()
                val cyanEtch = Color(0x8000E5FF)
                // Top-left
                drawLine(cyanEtch, Offset(4f, 4f), Offset(4f + bSize, 4f), 1.5f)
                drawLine(cyanEtch, Offset(4f, 4f), Offset(4f, 4f + bSize), 1.5f)
                // Top-right
                drawLine(cyanEtch, Offset(w - 4f, 4f), Offset(w - 4f - bSize, 4f), 1.5f)
                drawLine(cyanEtch, Offset(w - 4f, 4f), Offset(w - 4f, 4f + bSize), 1.5f)
                // Bottom-left
                drawLine(cyanEtch, Offset(4f, h - 4f), Offset(4f + bSize, h - 4f), 1.5f)
                drawLine(cyanEtch, Offset(4f, h - 4f), Offset(4f, h - 4f - bSize), 1.5f)
                // Bottom-right
                drawLine(cyanEtch, Offset(w - 4f, h - 4f), Offset(w - 4f - bSize, h - 4f), 1.5f)
                drawLine(cyanEtch, Offset(w - 4f, h - 4f), Offset(w - 4f, h - 4f - bSize), 1.5f)

                // Moving laser scan line
                val scanY = h * gridSweep
                drawLine(
                    color = Color(0x2200E5FF),
                    start = Offset(0f, scanY),
                    end = Offset(w, scanY),
                    strokeWidth = 2f
                )
            }
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onCyclePane
            )
            .padding(10.dp)
    ) {
        Column {
            // Header: Holotable Status Bar + Tap Cycle Hint
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF00E5FF))
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "HOLOTABLE OPS MUX",
                        color = Color(0xFF00E5FF),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Monospace,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "[GLASS ETCH v2]",
                        color = Color(0x9900E5FF),
                        fontSize = 8.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0x3300E5FF))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Cycle Pane",
                        tint = Color(0xFF00E5FF),
                        modifier = Modifier.size(10.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "TAP TO CYCLE",
                        color = Color(0xFFE0F7FA),
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // 4 Panes in 2x2 Matrix (Etched into Glass)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                HolotablePaneCell(
                    pane = OpsPaneType.SHORTS,
                    isSelected = opsFeed.selectedPane == OpsPaneType.SHORTS,
                    onClick = { onSelectPane(OpsPaneType.SHORTS) },
                    modifier = Modifier.weight(1f)
                )
                HolotablePaneCell(
                    pane = OpsPaneType.ETSY,
                    isSelected = opsFeed.selectedPane == OpsPaneType.ETSY,
                    onClick = { onSelectPane(OpsPaneType.ETSY) },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                HolotablePaneCell(
                    pane = OpsPaneType.SPORTS,
                    isSelected = opsFeed.selectedPane == OpsPaneType.SPORTS,
                    onClick = { onSelectPane(OpsPaneType.SPORTS) },
                    modifier = Modifier.weight(1f)
                )
                HolotablePaneCell(
                    pane = OpsPaneType.BURN,
                    isSelected = opsFeed.selectedPane == OpsPaneType.BURN,
                    onClick = { onSelectPane(OpsPaneType.BURN) },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Marquee CTQ Stream (Ticking beneath the panes)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0x3302090F))
                    .border(0.5.dp, Color(0x3300E5FF), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 3.dp)
            ) {
                val activeIndex = opsFeed.selectedPane.ordinal
                val ctqLine = opsFeed.marqueeTicks.getOrElse(activeIndex) { opsFeed.marqueeTicks.first() }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = ctqLine,
                        color = Color(0xFF00E5FF),
                        fontSize = 8.5.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1
                    )
                    Text(
                        text = "MOCK",
                        color = Color(0xFFFFB300),
                        fontSize = 7.5.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }
    }
}

@Composable
private fun HolotablePaneCell(
    pane: OpsPaneType,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor by animateColorAsState(
        targetValue = if (isSelected) Color(0xFF00E5FF) else Color(0x2200E5FF),
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "cellBorder"
    )

    val bgColor by animateColorAsState(
        targetValue = if (isSelected) Color(0x3300E5FF) else Color(0x1800E5FF),
        animationSpec = tween(durationMillis = 300),
        label = "cellBg"
    )

    Box(
        modifier = modifier
            .testTag("holotable_pane_${pane.name.lowercase()}")
            .clip(RoundedCornerShape(6.dp))
            .background(bgColor)
            .border(if (isSelected) 1.5.dp else 0.75.dp, borderColor, RoundedCornerShape(6.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 6.dp, vertical = 5.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pane.title,
                    color = if (isSelected) Color(0xFFFFFFFF) else Color(0xCC00E5FF),
                    fontSize = 9.5.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 0.5.sp
                )
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(2.dp))
                            .background(Color(0xFF00E5FF))
                            .padding(horizontal = 3.dp, vertical = 1.dp)
                    ) {
                        Text(
                            text = "LIVE MOCK",
                            color = Color(0xFF040E14),
                            fontSize = 6.5.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(2.dp))

            // Metric A
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = pane.metricA.first.replace(" (MOCK)", ""),
                    color = Color(0x99A5F3FC),
                    fontSize = 7.5.sp,
                    fontFamily = FontFamily.Monospace
                )
                Text(
                    text = pane.metricA.second,
                    color = if (isSelected) Color(0xFF38BDF8) else Color(0xFFE2E8F0),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
            }

            // Metric B
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = pane.metricB.first.replace(" (MOCK)", ""),
                    color = Color(0x99A5F3FC),
                    fontSize = 7.5.sp,
                    fontFamily = FontFamily.Monospace
                )
                Text(
                    text = pane.metricB.second,
                    color = Color(0xFFCBD5E1),
                    fontSize = 7.5.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
        }
    }
}
