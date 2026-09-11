package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CrewMember
import com.example.ui.components.MockBanner
import com.example.ui.components.PixelSprite
import com.example.ui.util.SpriteRenderMode

/**
 * Chamber / Tactical Dossier Screen
 *
 * Full-screen dark tactical dossier for the selected crew agent:
 * - High-resolution 2D Pixel Art preview with look-lock fidelity
 * - Procedural Personality Module (Temperament, Quirk, Signature Grunt, Current Subroutine, etc.)
 * - Tactical Specs & Clearance
 * - Handoff: Chief -> https://t.me/Mindbangerbot ; others -> TELEGRAM VIA CHIEF
 * - Controls: "← FLOOR" and "ENTER CHAMBER" above Android nav safe area
 */
@Composable
fun DossierScreen(
    crew: CrewMember,
    onBackToFloor: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var spriteMode by remember { mutableStateOf(SpriteRenderMode.FULL_BODY) }

    // Ambient CRT radar sweep
    val infiniteTransition = rememberInfiniteTransition(label = "dossier_crt")
    val sweepY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "sweepY"
    )

    Box(
        modifier = modifier
            .testTag("dossier_screen_root")
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF060B10),
                        Color(0xFF09121B),
                        Color(0xFF04080D)
                    )
                )
            )
            .drawBehind {
                // Subtle scanline overlay
                val step = 4.dp.toPx()
                var y = 0f
                while (y <= size.height) {
                    drawLine(Color(0x06FFFFFF), Offset(0f, y), Offset(size.width, y), 0.5f)
                    y += step
                }
                // Radar sweep line
                val sY = size.height * sweepY
                drawLine(Color(0x1200E5FF), Offset(0f, sY), Offset(size.width, sY), 1.5f)
            }
            .navigationBarsPadding() // Above system nav bar
            .padding(horizontal = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 640.dp)
                .align(Alignment.TopCenter)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Persistent MOCK Banner
            MockBanner()

            Spacer(modifier = Modifier.height(10.dp))

            // Top Bar: Navigation back + Tactical ID Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = onBackToFloor,
                    modifier = Modifier
                        .testTag("back_to_floor_button"),
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF00E5FF)
                    ),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x6600E5FF))
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back to Floor",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "← FLOOR",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Monospace
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "DOSSIER: ${crew.callsign}",
                        color = Color(0xFFFFFFFF),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Monospace,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "CLEARANCE: ${crew.opsClearance}",
                        color = Color(0xFF00E5FF),
                        fontSize = 8.5.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Agent Hero Card (Pixel Art + Callsign + Look Lock Silhouette)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF0B1622))
                    .border(1.dp, Color(0x5500E5FF), RoundedCornerShape(10.dp))
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Left: Large Pixel Sprite Presentation with Mode Toggle
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(112.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFF050D15))
                                .border(1.dp, Color(0x3300E5FF), RoundedCornerShape(8.dp))
                                .padding(6.dp),
                            contentAlignment = if (spriteMode == SpriteRenderMode.FULL_BODY) Alignment.BottomCenter else Alignment.Center
                        ) {
                            PixelSprite(
                                crew = crew,
                                size = 100.dp,
                                renderMode = spriteMode,
                                ambientCyanGlow = true
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        // Compact Tactical View Switcher: Full-Body vs. Avatar
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(if (spriteMode == SpriteRenderMode.FULL_BODY) Color(0xFF00E5FF) else Color(0x2200E5FF))
                                    .clickable { spriteMode = SpriteRenderMode.FULL_BODY }
                                    .padding(horizontal = 6.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = "FULL",
                                    fontSize = 7.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Monospace,
                                    color = if (spriteMode == SpriteRenderMode.FULL_BODY) Color(0xFF0B1622) else Color(0xFF94A3B8)
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(if (spriteMode == SpriteRenderMode.AVATAR) Color(0xFF00E5FF) else Color(0x2200E5FF))
                                    .clickable { spriteMode = SpriteRenderMode.AVATAR }
                                    .padding(horizontal = 6.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = "AVATAR",
                                    fontSize = 7.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Monospace,
                                    color = if (spriteMode == SpriteRenderMode.AVATAR) Color(0xFF0B1622) else Color(0xFF94A3B8)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // Right: Primary Agent Identifiers
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = crew.callsign,
                                color = Color(0xFFFFFFFF),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Black,
                                fontFamily = FontFamily.Monospace
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(Color(0x3300E5FF))
                                    .padding(horizontal = 4.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = crew.genderPronouns,
                                    color = Color(0xFF00E5FF),
                                    fontSize = 7.5.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                        }

                        Text(
                            text = "${crew.species} // Codename: ${crew.codename}",
                            color = Color(0xFF38BDF8),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily.Monospace
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = crew.role,
                            color = Color(0xFFE2E8F0),
                            fontSize = 10.5.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = FontFamily.Monospace
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        // Duty lane pill
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color(0x22132A54))
                                .border(1.dp, Color(0x4400E5FF), RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "LANE: ${crew.dutyLane}",
                                color = Color(0xFFA5F3FC),
                                fontSize = 8.5.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Look Lock Compliance Box (Original character design fidelity)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF0A1118))
                    .border(1.dp, Color(0x2500E5FF), RoundedCornerShape(8.dp))
                    .padding(10.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Shield,
                            contentDescription = null,
                            tint = Color(0xFF00E5FF),
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "LOOK LOCK SPECIFICATION",
                            color = Color(0xFF00E5FF),
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = crew.lookLockSummary,
                        color = Color(0xFFCBD5E1),
                        fontSize = 9.5.sp,
                        fontFamily = FontFamily.Monospace,
                        lineHeight = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Procedural Personality Module (Deterministic Traits)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF0B141E))
                    .border(1.dp, Color(0x4400E5FF), RoundedCornerShape(8.dp))
                    .padding(10.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Psychology,
                                contentDescription = null,
                                tint = Color(0xFF00E5FF),
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "PROCEDURAL PERSONALITY MATRIX",
                                color = Color(0xFFFFFFFF),
                                fontSize = 9.5.sp,
                                fontWeight = FontWeight.Black,
                                fontFamily = FontFamily.Monospace
                            )
                        }

                        Text(
                            text = "SEED DETERMINISTIC",
                            color = Color(0x8000E5FF),
                            fontSize = 7.5.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    DossierTraitRow(label = "TEMPERAMENT", value = crew.temperament)
                    DossierTraitRow(label = "BEHAVIORAL QUIRK", value = crew.quirk)
                    DossierTraitRow(label = "SIGNATURE GRUNT", value = crew.signatureGrunt)
                    DossierTraitRow(label = "CURRENT SUBROUTINE", value = crew.currentSubroutine)
                    DossierTraitRow(label = "TACTICAL FOCUS", value = crew.tacticalFocus)
                    DossierTraitRow(label = "FUEL / CAFFEINE", value = crew.caffeineLevel)

                    Spacer(modifier = Modifier.height(6.dp))

                    // Vigilance Rating Progress Bar
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "VIGILANCE RATING: ${crew.vigilanceRating}%",
                            color = Color(0xFFA5F3FC),
                            fontSize = 8.5.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(Color(0xFF070E16))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(crew.vigilanceRating / 100f)
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(Color(0xFF00E5FF))
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Primary Tactical Action Bar: Telegram Handoff & Enter Chamber
            Column(modifier = Modifier.fillMaxWidth()) {
                if (crew.isChief) {
                    // Chief Direct Telegram CTA
                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(crew.telegramUplink))
                            try {
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                // Fallback
                            }
                        },
                        modifier = Modifier
                            .testTag("telegram_cta_button")
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0284C7),
                            contentColor = Color.White
                        )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Telegram",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "TRANSMIT VIA TELEGRAM (Chief Direct)",
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Monospace,
                            letterSpacing = 0.5.sp
                        )
                    }
                } else {
                    // Non-Chief crew member Telegram routed via Chief
                    Button(
                        onClick = {
                            // Route through Chief Mindbangerbot
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/Mindbangerbot"))
                            try {
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                // Fallback
                            }
                        },
                        modifier = Modifier
                            .testTag("telegram_cta_button")
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1E293B),
                            contentColor = Color(0xFF38BDF8)
                        ),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x6600E5FF))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Routed Channel",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "TELEGRAM VIA CHIEF (@Mindbangerbot)",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Monospace,
                            letterSpacing = 0.5.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Enter Chamber / Floor return dual actions
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = onBackToFloor,
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp),
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color(0xFF94A3B8)
                        ),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x33FFFFFF))
                    ) {
                        Text(
                            text = "← RETURN TO FLOOR",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                    }

                    Button(
                        onClick = onBackToFloor,
                        modifier = Modifier
                            .testTag("enter_chamber_button")
                            .weight(1f)
                            .height(44.dp),
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0B3045),
                            contentColor = Color(0xFF00E5FF)
                        ),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF00E5FF))
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "STATION DUTY ACTIVE",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun DossierTraitRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 3.dp)) {
        Text(
            text = label,
            color = Color(0xFF00E5FF),
            fontSize = 7.5.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = value,
            color = Color(0xFFE2E8F0),
            fontSize = 9.sp,
            fontFamily = FontFamily.Monospace,
            lineHeight = 13.sp
        )
    }
}
