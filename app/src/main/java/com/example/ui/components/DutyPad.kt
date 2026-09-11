package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ripple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CrewMember

/**
 * Duty Pad Component
 * Fixed tactical duty station on the CIC floor around the holotable.
 * Displays callsign, status beacon, 2D pixel sprite, and soft-pulses
 * when its linked ops pane is focused.
 */
@Composable
fun DutyPad(
    crew: CrewMember,
    isPulsing: Boolean,
    onSelect: (CrewMember) -> Unit,
    modifier: Modifier = Modifier,
    padWidth: Dp = 68.dp,
    spriteSize: Dp = 80.dp,
    isChiefPatrolling: Boolean = false,
    ambientCyanGlow: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }

    // Pad border & glow animation when pulsing
    val pulseBorderColor by animateColorAsState(
        targetValue = if (isPulsing) Color(0xFF00E5FF) else Color(0x3300E5FF),
        animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing),
        label = "pulseBorder"
    )

    val padGlowAlpha by animateFloatAsState(
        targetValue = if (isPulsing) 0.45f else 0.12f,
        animationSpec = tween(durationMillis = 350),
        label = "glowAlpha"
    )

    val scaleBoost by animateFloatAsState(
        targetValue = if (isPulsing) 1.04f else 1.0f,
        animationSpec = tween(durationMillis = 350),
        label = "scaleBoost"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .testTag("duty_pad_${crew.id}")
            .scale(scaleBoost)
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(bounded = false, radius = 40.dp),
                onClick = { onSelect(crew) }
            )
    ) {
        // Sprite on top of pad
        val spriteHeight = spriteSize * (420f / 320f)
        Box(
            modifier = Modifier
                .height(spriteHeight)
                .width(spriteSize),
            contentAlignment = Alignment.BottomCenter
        ) {
            if (!isChiefPatrolling) {
                PixelSprite(
                    crew = crew,
                    size = spriteSize,
                    ambientCyanGlow = ambientCyanGlow
                )
            } else {
                // Chief is actively patrolling the floor corridor, leaving an authenticated standby hologram on pad
                Box(
                    modifier = Modifier
                        .size(spriteSize * 0.75f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0x1500E5FF))
                        .border(1.dp, Color(0x3300E5FF), RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "PATROLLING",
                            fontSize = 8.sp,
                            color = Color(0xFF00E5FF),
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                        Text(
                            text = "STANDBY",
                            fontSize = 7.sp,
                            color = Color(0x9900E5FF),
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(2.dp))

        // Tactile Floor Pad Plate
        Box(
            modifier = Modifier
                .width(padWidth)
                .clip(RoundedCornerShape(4.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF141E28),
                            Color(0xFF0A1017)
                        )
                    )
                )
                .border(1.dp, pulseBorderColor, RoundedCornerShape(4.dp))
                .drawBehind {
                    // Holographic surface wash
                    drawRect(
                        color = Color(0x00E5FF).copy(alpha = padGlowAlpha),
                        topLeft = Offset.Zero,
                        size = size
                    )
                    // Tactical corner notches
                    val notch = 4.dp.toPx()
                    drawLine(Color(0xFF00E5FF), Offset(0f, 0f), Offset(notch, 0f), strokeWidth = 1.5f)
                    drawLine(Color(0xFF00E5FF), Offset(0f, 0f), Offset(0f, notch), strokeWidth = 1.5f)
                    drawLine(Color(0xFF00E5FF), Offset(size.width, 0f), Offset(size.width - notch, 0f), strokeWidth = 1.5f)
                    drawLine(Color(0xFF00E5FF), Offset(size.width, 0f), Offset(size.width, notch), strokeWidth = 1.5f)
                }
                .padding(horizontal = 4.dp, vertical = 3.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // Online telemetry status dot
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(if (isPulsing) Color(0xFF00E5FF) else Color(0xFF10B981))
                )

                Spacer(modifier = Modifier.width(3.dp))

                Text(
                    text = crew.callsign,
                    color = if (isPulsing) Color(0xFFFFFFFF) else Color(0xFFCBD5E1),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = 0.5.sp,
                    maxLines = 1
                )
            }
        }
    }
}
