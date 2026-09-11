package com.example.ui.components.sprites

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke

/**
 * Cudi — Midnight Wolf.
 * 64-Bit HD 3D Character Model (Star Fox 64 Style in High Definition).
 *
 * Baseline Standard Height: 1.0f scale reference for the crew.
 *
 * Sculpted 3D Volumetric Features:
 * - Charcoal wolf anatomy with frosted silver fur gradients along the cheeks and crest.
 * - Pointed 3D wolf ears protruding through tailored slits in a navy knit watch cap.
 * - Luminous ice-blue 3D eyes with eyeliner fur markings and inner glow.
 * - Solid silver 3D crescent moon pendant on black leather cord with metallic specular gleam.
 * - Midnight-blue bomber jacket with cyan diamond-quilted thread highlights and center zipper.
 * - Bushy charcoal 3D wolf tail with frosted silver-white tip.
 * - Dark tapered utility pants and midnight-blue retro high-top sneakers.
 * - Soft underfoot ambient occlusion contact shadow.
 */
object Cudi3dPalette {
    val FurCharcoalLight = Color(0xFF94A3B8)
    val FurCharcoal = Color(0xFF475569)
    val FurCharcoalDark = Color(0xFF1E293B)
    val FurFrosted = Color(0xFFF1F5F9)

    val BeanieNavy = Color(0xFF1E3A8A)
    val BeanieNavyLight = Color(0xFF2563EB)

    val EyeIceBlue = Color(0xFF38BDF8)
    val EyeIceGlow = Color(0xFFBAE6FD)

    val PendantSilver = Color(0xFFE2E8F0)
    val PendantGleam = Color(0xFFFFFFFF)

    val JacketMidnightLight = Color(0xFF1E40AF)
    val JacketMidnight = Color(0xFF1E3A8A)
    val JacketMidnightDark = Color(0xFF172554)
    val QuiltCyan = Color(0xFF00E5FF)
}

fun DrawScope.drawCudiFullBody(
    breatheY: Float,
    walkOffset: Float,
    isWalking: Boolean,
    isBlinking: Boolean
) {
    val bY = breatheY
    val leftLegWalk = walkOffset
    val rightLegWalk = -walkOffset

    // 0. 3D Ground Contact Shadow
    draw3dContactShadow(160f, 382f, radiusX = 66f, radiusY = 14f)

    // 1. Bushy 3D Wolf Tail with Frosted Tip (Curving behind viewer's right)
    val tail = Path().apply {
        moveTo(178f, 250f + bY)
        cubicTo(238f, 220f, 288f, 270f, 268f, 350f)
        cubicTo(245f, 380f, 215f, 330f, 175f, 270f + bY)
        close()
    }
    drawPath(
        tail,
        brush = Brush.radialGradient(
            colors = listOf(Cudi3dPalette.FurCharcoal, Cudi3dPalette.FurCharcoalDark, Color(0xFF0F172A)),
            center = Offset(220f, 280f),
            radius = 80f
        ),
        style = Fill
    )
    val tailTip = Path().apply {
        moveTo(248f, 320f)
        cubicTo(278f, 340f, 268f, 365f, 245f, 375f)
        cubicTo(235f, 355f, 238f, 335f, 248f, 320f)
        close()
    }
    drawPath(
        tailTip,
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFFFFFFFF), Cudi3dPalette.FurFrosted, Cudi3dPalette.FurCharcoalLight),
            center = Offset(250f, 345f),
            radius = 35f
        ),
        style = Fill
    )

    // 2. Dark Tapered Utility Pants Legs
    val leftLeg = Path().apply {
        moveTo(122f, 224f + bY * 0.4f)
        lineTo(110f, 276f + leftLegWalk * 0.5f)
        lineTo(112f, 350f + leftLegWalk)
        lineTo(146f, 350f + leftLegWalk)
        lineTo(144f, 280f + leftLegWalk * 0.5f)
        lineTo(158f, 228f + bY * 0.4f)
        close()
    }
    draw3dCylinder(leftLeg, Cudi3dPalette.FurCharcoalDark, Color(0xFF334155), Color(0xFF0F172A), isLeftLimb = true)

    val rightLeg = Path().apply {
        moveTo(164f, 228f + bY * 0.4f)
        lineTo(176f, 280f + rightLegWalk * 0.5f)
        lineTo(174f, 350f + rightLegWalk)
        lineTo(208f, 350f + rightLegWalk)
        lineTo(210f, 276f + rightLegWalk * 0.5f)
        lineTo(198f, 224f + bY * 0.4f)
        close()
    }
    draw3dCylinder(rightLeg, Cudi3dPalette.FurCharcoalDark, Color(0xFF334155), Color(0xFF0F172A), isLeftLimb = false)

    // Midnight-Blue Retro High-Tops
    draw3dHighTopSneaker(
        x = 90f,
        y = 350f + leftLegWalk,
        width = 56f,
        height = 36f,
        primaryLight = Cudi3dPalette.JacketMidnightLight,
        primaryMid = Cudi3dPalette.JacketMidnight,
        primaryDark = Cudi3dPalette.JacketMidnightDark,
        accentColor = Color(0xFFF8FAFC),
        facingRight = false
    )
    draw3dHighTopSneaker(
        x = 172f,
        y = 350f + rightLegWalk,
        width = 56f,
        height = 36f,
        primaryLight = Cudi3dPalette.JacketMidnightLight,
        primaryMid = Cudi3dPalette.JacketMidnight,
        primaryDark = Cudi3dPalette.JacketMidnightDark,
        accentColor = Color(0xFFF8FAFC),
        facingRight = true
    )

    // 3. Midnight-Blue Bomber Jacket & Crescent Moon Pendant
    val torsoY = 96f + bY

    val bomber = Path().apply {
        moveTo(102f, torsoY + 16f)
        cubicTo(120f, torsoY + 12f, 200f, torsoY + 12f, 218f, torsoY + 16f)
        lineTo(206f, torsoY + 130f)
        lineTo(114f, torsoY + 130f)
        close()
    }
    drawPath(
        bomber,
        brush = Brush.radialGradient(
            colors = listOf(Cudi3dPalette.JacketMidnightLight, Cudi3dPalette.JacketMidnight, Cudi3dPalette.JacketMidnightDark),
            center = Offset(150f, torsoY + 45f),
            radius = 110f
        ),
        style = Fill
    )

    // 3D Center Zipper
    draw3dZipper(160f, torsoY + 16f, torsoY + 128f, pullTabY = torsoY + 50f, metallicColor = Hd3dPalettes.SilverLight)

    // Cyan Diamond-Quilting Highlight Stitches
    for (d in 0..2) {
        val qy = torsoY + 45f + d * 25f
        drawLine(Cudi3dPalette.QuiltCyan.copy(alpha = 0.4f), Offset(132f, qy), Offset(148f, qy + 12f), strokeWidth = 1.4f)
        drawLine(Cudi3dPalette.QuiltCyan.copy(alpha = 0.4f), Offset(148f, qy + 12f), Offset(132f, qy + 24f), strokeWidth = 1.4f)
        drawLine(Cudi3dPalette.QuiltCyan.copy(alpha = 0.4f), Offset(188f, qy), Offset(172f, qy + 12f), strokeWidth = 1.4f)
        drawLine(Cudi3dPalette.QuiltCyan.copy(alpha = 0.4f), Offset(172f, qy + 12f), Offset(188f, qy + 24f), strokeWidth = 1.4f)
    }

    // Solid Silver 3D Crescent Moon Pendant
    val moonCenter = Offset(160f, torsoY + 36f)
    draw3dSphere(moonCenter, radius = 6f, baseColor = Cudi3dPalette.PendantSilver, highlightColor = Cudi3dPalette.PendantGleam, shadowColor = Color(0xFF64748B))

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
    draw3dCylinder(leftArm, Cudi3dPalette.JacketMidnight, Cudi3dPalette.JacketMidnightLight, Cudi3dPalette.JacketMidnightDark, isLeftLimb = true)
    draw3dSphere(Offset(102f, torsoY + 116f), radius = 9f, baseColor = Cudi3dPalette.FurCharcoal, highlightColor = Cudi3dPalette.FurCharcoalLight, shadowColor = Cudi3dPalette.FurCharcoalDark)

    val rightArm = Path().apply {
        moveTo(218f, torsoY + 16f)
        lineTo(240f, torsoY + 70f)
        lineTo(222f, torsoY + 112f)
        lineTo(204f, torsoY + 106f)
        lineTo(218f, torsoY + 70f)
        lineTo(204f, torsoY + 24f)
        close()
    }
    draw3dCylinder(rightArm, Cudi3dPalette.JacketMidnight, Cudi3dPalette.JacketMidnightLight, Cudi3dPalette.JacketMidnightDark, isLeftLimb = false)
    draw3dSphere(Offset(216f, torsoY + 116f), radius = 9f, baseColor = Cudi3dPalette.FurCharcoal, highlightColor = Cudi3dPalette.FurCharcoalLight, shadowColor = Cudi3dPalette.FurCharcoalDark)

    // 4. Wolf Head, Slitted Watch Cap & Luminous Ice Eyes
    val headY = 18f + bY

    // Pointed Wolf Ears protruding through cap slits
    val leftEar = Path().apply {
        moveTo(114f, headY + 28f)
        lineTo(92f, headY - 10f)
        lineTo(132f, headY + 10f)
        close()
    }
    drawPath(leftEar, color = Cudi3dPalette.FurCharcoalDark, style = Fill)
    val leftEarInner = Path().apply {
        moveTo(112f, headY + 22f)
        lineTo(98f, headY - 2f)
        lineTo(126f, headY + 10f)
        close()
    }
    drawPath(leftEarInner, color = Cudi3dPalette.FurFrosted, style = Fill)

    val rightEar = Path().apply {
        moveTo(206f, headY + 28f)
        lineTo(228f, headY - 10f)
        lineTo(188f, headY + 10f)
        close()
    }
    drawPath(rightEar, color = Cudi3dPalette.FurCharcoalDark, style = Fill)
    val rightEarInner = Path().apply {
        moveTo(208f, headY + 22f)
        lineTo(222f, headY - 2f)
        lineTo(194f, headY + 10f)
        close()
    }
    drawPath(rightEarInner, color = Cudi3dPalette.FurFrosted, style = Fill)

    // Navy Knit Watch Cap
    val cap = Path().apply {
        moveTo(114f, headY + 28f)
        cubicTo(122f, headY + 4f, 198f, headY + 4f, 206f, headY + 28f)
        lineTo(198f, headY + 36f)
        cubicTo(180f, headY + 32f, 140f, headY + 32f, 122f, headY + 36f)
        close()
    }
    drawPath(
        cap,
        brush = Brush.radialGradient(
            colors = listOf(Cudi3dPalette.BeanieNavyLight, Cudi3dPalette.BeanieNavy, Color(0xFF0F172A)),
            center = Offset(150f, headY + 12f),
            radius = 55f
        ),
        style = Fill
    )

    // Wolf Head Volume
    val head = Path().apply {
        moveTo(114f, headY + 28f)
        cubicTo(124f, headY + 8f, 196f, headY + 8f, 206f, headY + 28f)
        cubicTo(220f, headY + 50f, 218f, headY + 72f, 202f, headY + 84f)
        lineTo(160f, headY + 86f)
        lineTo(118f, headY + 84f)
        cubicTo(102f, headY + 72f, 100f, headY + 50f, 114f, headY + 28f)
        close()
    }
    drawPath(
        head,
        brush = Brush.radialGradient(
            colors = listOf(Cudi3dPalette.FurCharcoalLight, Cudi3dPalette.FurCharcoal, Cudi3dPalette.FurCharcoalDark),
            center = Offset(150f, headY + 45f),
            radius = 65f
        ),
        style = Fill
    )

    // Silver-Frosted Fur Accents on Cheeks
    draw3dSphere(Offset(124f, headY + 65f), radius = 8f, baseColor = Cudi3dPalette.FurFrosted, highlightColor = Color(0xFFFFFFFF), shadowColor = Cudi3dPalette.FurCharcoalLight, hasSpecular = false)
    draw3dSphere(Offset(196f, headY + 65f), radius = 8f, baseColor = Cudi3dPalette.FurFrosted, highlightColor = Color(0xFFFFFFFF), shadowColor = Cudi3dPalette.FurCharcoalLight, hasSpecular = false)

    // Wolf Muzzle & Nose
    val muzzle = Path().apply {
        moveTo(134f, headY + 52f)
        lineTo(186f, headY + 52f)
        cubicTo(180f, headY + 76f, 172f, headY + 84f, 160f, headY + 86f)
        cubicTo(148f, headY + 84f, 140f, headY + 76f, 134f, headY + 52f)
        close()
    }
    drawPath(
        muzzle,
        brush = Brush.radialGradient(
            colors = listOf(Cudi3dPalette.FurCharcoalLight, Cudi3dPalette.FurCharcoal, Cudi3dPalette.FurCharcoalDark),
            center = Offset(160f, headY + 68f),
            radius = 35f
        ),
        style = Fill
    )
    draw3dSphere(Offset(160f, headY + 62f), radius = 5.5f, baseColor = Color(0xFF1E293B), highlightColor = Color(0xFF475569), shadowColor = Color(0xFF05080E))

    // Luminous Ice-Blue 3D Eyes
    if (!isBlinking) {
        draw3dEye(
            center = Offset(136f, headY + 46f),
            radius = 6.2f,
            irisColor = Cudi3dPalette.EyeIceBlue,
            irisGlowColor = Cudi3dPalette.EyeIceGlow
        )
        draw3dEye(
            center = Offset(184f, headY + 46f),
            radius = 6.2f,
            irisColor = Cudi3dPalette.EyeIceBlue,
            irisGlowColor = Cudi3dPalette.EyeIceGlow
        )
    }
}

fun DrawScope.drawCudiAvatarPortrait(isBlinking: Boolean) {
    // 320x320 64-Bit HD 3D Portrait View (Wolf Ears, Beanie, Ice-Blue Eyes, Silver Crescent Moon)
    val head = Path().apply { moveTo(90f, 90f); cubicTo(110f, 30f, 210f, 30f, 230f, 90f); lineTo(250f, 160f); lineTo(225f, 220f); lineTo(160f, 230f); lineTo(95f, 220f); lineTo(70f, 160f); close() }
    drawPath(head, brush = Brush.radialGradient(colors = listOf(Cudi3dPalette.FurCharcoalLight, Cudi3dPalette.FurCharcoal, Cudi3dPalette.FurCharcoalDark), center = Offset(150f, 120f), radius = 120f), style = Fill)

    // Beanie
    val cap = Path().apply { moveTo(80f, 95f); cubicTo(100f, 20f, 220f, 20f, 240f, 95f); close() }
    drawPath(cap, brush = Brush.radialGradient(colors = listOf(Cudi3dPalette.BeanieNavyLight, Cudi3dPalette.BeanieNavy, Color(0xFF0F172A)), center = Offset(160f, 50f), radius = 90f), style = Fill)

    // Silver Crescent Moon Pendant
    draw3dSphere(Offset(160f, 240f), radius = 12f, baseColor = Cudi3dPalette.PendantSilver, highlightColor = Cudi3dPalette.PendantGleam, shadowColor = Color(0xFF64748B))

    // Eyes
    if (!isBlinking) {
        draw3dEye(center = Offset(120f, 135f), radius = 13f, irisColor = Cudi3dPalette.EyeIceBlue, irisGlowColor = Cudi3dPalette.EyeIceGlow)
        draw3dEye(center = Offset(200f, 135f), radius = 13f, irisColor = Cudi3dPalette.EyeIceBlue, irisGlowColor = Cudi3dPalette.EyeIceGlow)
    }

    // Jacket
    val bomber = Path().apply { moveTo(40f, 320f); lineTo(280f, 320f); lineTo(250f, 245f); lineTo(70f, 245f); close() }
    drawPath(bomber, brush = Brush.radialGradient(colors = listOf(Cudi3dPalette.JacketMidnightLight, Cudi3dPalette.JacketMidnight, Cudi3dPalette.JacketMidnightDark), center = Offset(160f, 275f), radius = 120f), style = Fill)
}
