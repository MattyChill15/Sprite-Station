package com.example.ui.components.sprites

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke

/**
 * Dodge — Shepherd Tactical Comms Dispatcher.
 * 64-Bit HD 3D Character Model (Star Fox 64 Style in High Definition).
 *
 * Sculpted 3D Volumetric Features:
 * - Tri-color Shepherd anatomy: warm tan cheeks, black muzzle mask, alert tan eyebrow pips.
 * - Pointed 3D ears erect in hyper-vigilant stance.
 * - Professional tactical broadcast headset: padded arch, sculpted earcups, flexible curved
 *   boom microphone with a glowing cyan comms LED at the tip.
 * - High-visibility hazard-red shoulder yoke over dark charcoal softshell tactical jacket with 3D zipper.
 * - Center-chest tactical radio comms pack with whip antenna and status readouts.
 * - Combat utility cargo pants and 3D speed-laced combat boots.
 * - Soft underfoot ambient occlusion contact shadow.
 */
object Dodge3dPalette {
    val FurBlack = Color(0xFF1E293B)
    val FurTanLight = Color(0xFFF59E0B)
    val FurTanMid = Color(0xFFD97706)
    val FurTanShadow = Color(0xFF92400E)

    val HeadsetDark = Color(0xFF0F172A)
    val HeadsetSilver = Color(0xFFCBD5E1)
    val LedCyan = Color(0xFF00E5FF)

    val YokeRed = Color(0xFFDC2626)
    val YokeLight = Color(0xFFEF4444)
    val JacketCharcoal = Color(0xFF1E293B)
    val JacketShadow = Color(0xFF0F172A)

    val PantsCargo = Color(0xFF334155)
}

fun DrawScope.drawDodgeFullBody(
    breatheY: Float,
    walkOffset: Float,
    isWalking: Boolean,
    isBlinking: Boolean
) {
    val bY = breatheY
    val leftLegWalk = walkOffset
    val rightLegWalk = -walkOffset

    // 0. 3D Ground Contact Shadow
    draw3dContactShadow(160f, 382f, radiusX = 64f, radiusY = 14f)

    // 1. Combat Utility Cargo Pants
    val leftLeg = Path().apply {
        moveTo(122f, 224f + bY * 0.4f)
        lineTo(110f, 276f + leftLegWalk * 0.5f)
        lineTo(112f, 350f + leftLegWalk)
        lineTo(146f, 350f + leftLegWalk)
        lineTo(144f, 280f + leftLegWalk * 0.5f)
        lineTo(158f, 228f + bY * 0.4f)
        close()
    }
    draw3dCylinder(leftLeg, Dodge3dPalette.PantsCargo, Color(0xFF475569), Dodge3dPalette.JacketShadow, isLeftLimb = true)

    val rightLeg = Path().apply {
        moveTo(164f, 228f + bY * 0.4f)
        lineTo(176f, 280f + rightLegWalk * 0.5f)
        lineTo(174f, 350f + rightLegWalk)
        lineTo(208f, 350f + rightLegWalk)
        lineTo(210f, 276f + rightLegWalk * 0.5f)
        lineTo(198f, 224f + bY * 0.4f)
        close()
    }
    draw3dCylinder(rightLeg, Dodge3dPalette.PantsCargo, Color(0xFF475569), Dodge3dPalette.JacketShadow, isLeftLimb = false)

    // 3D Speed-Laced Combat Boots
    draw3dCombatBoot(
        x = 90f,
        y = 350f + leftLegWalk,
        width = 56f,
        height = 36f,
        leatherLight = Color(0xFF334155),
        leatherMid = Color(0xFF1E293B),
        leatherDark = Color(0xFF0F172A),
        facingRight = false
    )
    draw3dCombatBoot(
        x = 172f,
        y = 350f + rightLegWalk,
        width = 56f,
        height = 36f,
        leatherLight = Color(0xFF334155),
        leatherMid = Color(0xFF1E293B),
        leatherDark = Color(0xFF0F172A),
        facingRight = true
    )

    // 2. Softshell Tactical Jacket & Hazard-Red Yoke
    val torsoY = 96f + bY

    // Charcoal Softshell Jacket Body
    val jacket = Path().apply {
        moveTo(102f, torsoY + 16f)
        cubicTo(120f, torsoY + 12f, 200f, torsoY + 12f, 218f, torsoY + 16f)
        lineTo(206f, torsoY + 130f)
        lineTo(114f, torsoY + 130f)
        close()
    }
    drawPath(
        jacket,
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFF334155), Dodge3dPalette.JacketCharcoal, Dodge3dPalette.JacketShadow),
            center = Offset(150f, torsoY + 45f),
            radius = 110f
        ),
        style = Fill
    )

    // High-Visibility Hazard-Red Shoulder Yoke
    val redYoke = Path().apply {
        moveTo(102f, torsoY + 16f)
        cubicTo(120f, torsoY + 12f, 200f, torsoY + 12f, 218f, torsoY + 16f)
        lineTo(214f, torsoY + 54f)
        cubicTo(180f, torsoY + 62f, 140f, torsoY + 62f, 106f, torsoY + 54f)
        close()
    }
    drawPath(
        redYoke,
        brush = Brush.horizontalGradient(
            colors = listOf(Dodge3dPalette.YokeLight, Dodge3dPalette.YokeRed, Color(0xFF991B1B)),
            startX = 100f,
            endX = 220f
        ),
        style = Fill
    )

    // 3D Center Zipper
    draw3dZipper(160f, torsoY + 16f, torsoY + 128f, pullTabY = torsoY + 55f, metallicColor = Hd3dPalettes.SilverLight)

    // Center Chest Tactical Comms Pack with Whip Antenna
    drawRoundRect(
        brush = Brush.verticalGradient(
            colors = listOf(Color(0xFF334155), Color(0xFF0F172A)),
            startY = torsoY + 65f,
            endY = torsoY + 105f
        ),
        topLeft = Offset(148f, torsoY + 65f),
        size = Size(24f, 38f),
        cornerRadius = CornerRadius(4f, 4f)
    )
    // Whip Antenna
    drawLine(
        color = Color(0xFF64748B),
        start = Offset(152f, torsoY + 65f),
        end = Offset(146f, torsoY + 30f),
        strokeWidth = 2.2f
    )
    // Glowing Cyan Comms Readout
    drawRoundRect(
        color = Dodge3dPalette.LedCyan,
        topLeft = Offset(152f, torsoY + 72f),
        size = Size(16f, 8f),
        cornerRadius = CornerRadius(1.5f, 1.5f)
    )

    // Arms
    val leftArm = Path().apply {
        moveTo(102f, torsoY + 16f)
        lineTo(76f, torsoY + 70f)
        lineTo(96f, torsoY + 112f)
        lineTo(116f, torsoY + 106f)
        lineTo(102f, torsoY + 70f)
        lineTo(116f, torsoY + 24f)
        close()
    }
    draw3dCylinder(leftArm, Dodge3dPalette.JacketCharcoal, Color(0xFF334155), Dodge3dPalette.JacketShadow, isLeftLimb = true)
    draw3dSphere(Offset(102f, torsoY + 116f), radius = 9f, baseColor = Dodge3dPalette.FurTanMid, highlightColor = Dodge3dPalette.FurTanLight, shadowColor = Dodge3dPalette.FurTanShadow)

    val rightArm = Path().apply {
        moveTo(218f, torsoY + 16f)
        lineTo(240f, torsoY + 70f)
        lineTo(222f, torsoY + 112f)
        lineTo(204f, torsoY + 106f)
        lineTo(218f, torsoY + 70f)
        lineTo(204f, torsoY + 24f)
        close()
    }
    draw3dCylinder(rightArm, Dodge3dPalette.JacketCharcoal, Color(0xFF334155), Dodge3dPalette.JacketShadow, isLeftLimb = false)
    draw3dSphere(Offset(216f, torsoY + 116f), radius = 9f, baseColor = Dodge3dPalette.FurTanMid, highlightColor = Dodge3dPalette.FurTanLight, shadowColor = Dodge3dPalette.FurTanShadow)

    // 3. Shepherd Head, Tactical Headset & Glowing Boom Mic
    val headY = 18f + bY

    // Pointed Shepherd Ears (Erect with tan inner ruff)
    val leftEar = Path().apply {
        moveTo(116f, headY + 28f)
        lineTo(96f, headY - 14f)
        lineTo(132f, headY + 12f)
        close()
    }
    drawPath(leftEar, color = Dodge3dPalette.FurBlack, style = Fill)
    val leftEarInner = Path().apply {
        moveTo(114f, headY + 24f)
        lineTo(102f, headY - 6f)
        lineTo(126f, headY + 12f)
        close()
    }
    drawPath(leftEarInner, color = Dodge3dPalette.FurTanMid, style = Fill)

    val rightEar = Path().apply {
        moveTo(204f, headY + 28f)
        lineTo(224f, headY - 14f)
        lineTo(188f, headY + 12f)
        close()
    }
    drawPath(rightEar, color = Dodge3dPalette.FurBlack, style = Fill)
    val rightEarInner = Path().apply {
        moveTo(206f, headY + 24f)
        lineTo(218f, headY - 6f)
        lineTo(194f, headY + 12f)
        close()
    }
    drawPath(rightEarInner, color = Dodge3dPalette.FurTanMid, style = Fill)

    // Head Volume
    val head = Path().apply {
        moveTo(114f, headY + 28f)
        cubicTo(124f, headY + 8f, 196f, headY + 8f, 206f, headY + 28f)
        cubicTo(220f, headY + 50f, 218f, headY + 74f, 202f, headY + 86f)
        lineTo(160f, headY + 88f)
        lineTo(118f, headY + 86f)
        cubicTo(102f, headY + 74f, 100f, headY + 50f, 114f, headY + 28f)
        close()
    }
    drawPath(
        head,
        brush = Brush.radialGradient(
            colors = listOf(Dodge3dPalette.FurTanLight, Dodge3dPalette.FurTanMid, Dodge3dPalette.FurTanShadow),
            center = Offset(150f, headY + 45f),
            radius = 65f
        ),
        style = Fill
    )

    // Black Muzzle & Mask
    val muzzle = Path().apply {
        moveTo(134f, headY + 50f)
        lineTo(186f, headY + 50f)
        cubicTo(180f, headY + 78f, 172f, headY + 86f, 160f, headY + 88f)
        cubicTo(148f, headY + 86f, 140f, headY + 78f, 134f, headY + 50f)
        close()
    }
    drawPath(
        muzzle,
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFF334155), Dodge3dPalette.FurBlack, Dodge3dPalette.JacketShadow),
            center = Offset(160f, headY + 65f),
            radius = 35f
        ),
        style = Fill
    )
    draw3dSphere(Offset(160f, headY + 62f), radius = 5.5f, baseColor = Color(0xFF1E293B), highlightColor = Color(0xFF475569), shadowColor = Color(0xFF05080E))

    // Alert Tan Eyebrow Pips
    draw3dSphere(Offset(136f, headY + 36f), radius = 3.5f, baseColor = Dodge3dPalette.FurTanMid, highlightColor = Dodge3dPalette.FurTanLight, shadowColor = Dodge3dPalette.FurTanShadow, hasSpecular = false)
    draw3dSphere(Offset(184f, headY + 36f), radius = 3.5f, baseColor = Dodge3dPalette.FurTanMid, highlightColor = Dodge3dPalette.FurTanLight, shadowColor = Dodge3dPalette.FurTanShadow, hasSpecular = false)

    // Eyes
    if (!isBlinking) {
        draw3dEye(center = Offset(136f, headY + 46f), radius = 6.2f, irisColor = Color(0xFF78350F), irisGlowColor = Color(0xFFB45309))
        draw3dEye(center = Offset(184f, headY + 46f), radius = 6.2f, irisColor = Color(0xFF78350F), irisGlowColor = Color(0xFFB45309))
    }

    // Professional Tactical Broadcast Headset & Glowing Cyan Boom Mic
    // Headband
    drawLine(
        brush = Brush.horizontalGradient(colors = listOf(Hd3dPalettes.SilverDark, Hd3dPalettes.SilverLight, Hd3dPalettes.SilverDark), startX = 104f, endX = 216f),
        start = Offset(108f, headY + 24f),
        end = Offset(212f, headY + 24f),
        strokeWidth = 4f
    )
    // Left Earcup
    draw3dSphere(Offset(108f, headY + 44f), radius = 9f, baseColor = Dodge3dPalette.HeadsetDark, highlightColor = Color(0xFF334155), shadowColor = Color(0xFF05080E))
    // Flexible Boom Mic curving to muzzle
    val boomMic = Path().apply {
        moveTo(108f, headY + 48f)
        cubicTo(116f, headY + 70f, 134f, headY + 74f, 152f, headY + 70f)
    }
    drawPath(boomMic, color = Color(0xFFCBD5E1), style = Stroke(width = 2.4f, cap = StrokeCap.Round))
    // Glowing Cyan Mic Tip
    draw3dSphere(Offset(152f, headY + 70f), radius = 3.5f, baseColor = Dodge3dPalette.LedCyan, highlightColor = Color(0xFFFFFFFF), shadowColor = Color(0xFF0891B2))
}

fun DrawScope.drawDodgeAvatarPortrait(isBlinking: Boolean) {
    // 320x320 64-Bit HD 3D Portrait View (Tactical Headset, Boom Mic, Red Yoke, Tan Pips)
    // Head Volume
    val head = Path().apply { moveTo(90f, 85f); cubicTo(110f, 25f, 210f, 25f, 230f, 85f); lineTo(250f, 160f); lineTo(225f, 220f); lineTo(160f, 230f); lineTo(95f, 220f); lineTo(70f, 160f); close() }
    drawPath(head, brush = Brush.radialGradient(colors = listOf(Dodge3dPalette.FurTanLight, Dodge3dPalette.FurTanMid, Dodge3dPalette.FurTanShadow), center = Offset(150f, 120f), radius = 120f), style = Fill)

    // Black Muzzle & Mask
    val muzzle = Path().apply { moveTo(120f, 140f); lineTo(200f, 140f); cubicTo(190f, 195f, 180f, 225f, 160f, 230f); cubicTo(140f, 225f, 130f, 195f, 120f, 140f); close() }
    drawPath(muzzle, brush = Brush.radialGradient(colors = listOf(Color(0xFF334155), Dodge3dPalette.FurBlack, Dodge3dPalette.JacketShadow), center = Offset(160f, 175f), radius = 65f), style = Fill)
    draw3dSphere(Offset(160f, 170f), radius = 12f, baseColor = Color(0xFF1E293B), highlightColor = Color(0xFF475569), shadowColor = Color(0xFF05080E))

    // Eyebrow Pips
    draw3dSphere(Offset(120f, 105f), radius = 8f, baseColor = Dodge3dPalette.FurTanMid, highlightColor = Dodge3dPalette.FurTanLight, shadowColor = Dodge3dPalette.FurTanShadow, hasSpecular = false)
    draw3dSphere(Offset(200f, 105f), radius = 8f, baseColor = Dodge3dPalette.FurTanMid, highlightColor = Dodge3dPalette.FurTanLight, shadowColor = Dodge3dPalette.FurTanShadow, hasSpecular = false)

    // Eyes
    if (!isBlinking) {
        draw3dEye(center = Offset(120f, 135f), radius = 13f, irisColor = Color(0xFF78350F), irisGlowColor = Color(0xFFB45309))
        draw3dEye(center = Offset(200f, 135f), radius = 13f, irisColor = Color(0xFF78350F), irisGlowColor = Color(0xFFB45309))
    }

    // Headset & Glowing Cyan Boom Mic
    draw3dSphere(Offset(70f, 135f), radius = 22f, baseColor = Dodge3dPalette.HeadsetDark, highlightColor = Color(0xFF334155), shadowColor = Color(0xFF05080E))
    val boomMic = Path().apply { moveTo(70f, 145f); cubicTo(90f, 200f, 120f, 210f, 148f, 195f) }
    drawPath(boomMic, color = Color(0xFFCBD5E1), style = Stroke(width = 5f, cap = StrokeCap.Round))
    draw3dSphere(Offset(148f, 195f), radius = 8f, baseColor = Dodge3dPalette.LedCyan, highlightColor = Color(0xFFFFFFFF), shadowColor = Color(0xFF0891B2))

    // Red Yoke & Jacket
    val yoke = Path().apply { moveTo(40f, 320f); lineTo(280f, 320f); lineTo(250f, 245f); lineTo(70f, 245f); close() }
    drawPath(yoke, brush = Brush.horizontalGradient(colors = listOf(Dodge3dPalette.YokeLight, Dodge3dPalette.YokeRed, Color(0xFF991B1B)), startX = 40f, endX = 280f), style = Fill)
}
