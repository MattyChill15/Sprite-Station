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
 * Shorty — Streetwear Red Fox.
 * 64-Bit HD 3D Character Model (Star Fox 64 Style in High Definition).
 *
 * Sculpted 3D Volumetric Features:
 * - Smooth russet-orange fox anatomy with sculpted white cheek ruffs and black-backed pointed 3D ears.
 * - Backward crimson 3D snapback cap with curved crown, reverse visor, and plastic adjuster notch strap.
 * - Glossy red over-ear 3D DJ headphones resting around neck with silver metallic headband.
 * - Smug confident 3D fox muzzle with black nose, sly smirk, and emerald-green 3D eyes.
 * - Heavy horizontal-baffle down puffer jacket in vivid red with right-sleeve white "OK" shield badge,
 *   and hands thrust deep into puffer side pockets.
 * - Volumetric olive drab military cargo pants with bellows pockets, and Chicago Red-and-White 3D high-tops.
 * - Sweeping bushy 3D red fox tail with fluffy white 3D tip.
 * - Soft underfoot ambient occlusion contact shadow.
 */
object Shorty3dPalette {
    val FurLight = Color(0xFFF97316)
    val FurMid = Color(0xFFEA580C)
    val FurShadow = Color(0xFFC2410C)
    val FurWhite = Color(0xFFF8FAFC)
    val EarBlack = Color(0xFF1E293B)

    val CapRedLight = Color(0xFFEF4444)
    val CapRedMid = Color(0xFFDC2626)
    val CapRedDark = Color(0xFF991B1B)

    val PufferLight = Color(0xFFF87171)
    val PufferRed = Color(0xFFEF4444)
    val PufferShadow = Color(0xFFB91C1C)

    val EyeGreen = Color(0xFF10B981)
    val EyeGreenGlow = Color(0xFF34D399)

    val CargoLight = Color(0xFF65A30D)
    val CargoMid = Color(0xFF4D7C0F)
    val CargoDark = Color(0xFF365314)
}

fun DrawScope.drawShortyFullBody(
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

    // 1. Sweeping Bushy 3D Fox Tail (Sweeping up and behind on viewer's right)
    val tail = Path().apply {
        moveTo(180f, 250f + bY)
        cubicTo(240f, 220f, 290f, 270f, 270f, 350f)
        cubicTo(245f, 380f, 215f, 330f, 175f, 270f + bY)
        close()
    }
    drawPath(
        tail,
        brush = Brush.radialGradient(
            colors = listOf(Shorty3dPalette.FurLight, Shorty3dPalette.FurMid, Shorty3dPalette.FurShadow),
            center = Offset(220f, 280f),
            radius = 80f
        ),
        style = Fill
    )
    val tailTip = Path().apply {
        moveTo(250f, 320f)
        cubicTo(280f, 340f, 270f, 365f, 245f, 375f)
        cubicTo(235f, 355f, 238f, 335f, 250f, 320f)
        close()
    }
    drawPath(
        tailTip,
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFFFFFFFF), Color(0xFFF1F5F9), Color(0xFFCBD5E1)),
            center = Offset(250f, 345f),
            radius = 35f
        ),
        style = Fill
    )

    // 2. Volumetric 3D Cargo Pants Legs
    val leftLeg = Path().apply {
        moveTo(122f, 224f + bY * 0.4f)
        lineTo(110f, 276f + leftLegWalk * 0.5f)
        lineTo(112f, 350f + leftLegWalk)
        lineTo(146f, 350f + leftLegWalk)
        lineTo(144f, 280f + leftLegWalk * 0.5f)
        lineTo(158f, 228f + bY * 0.4f)
        close()
    }
    draw3dCylinder(leftLeg, Shorty3dPalette.CargoMid, Shorty3dPalette.CargoLight, Shorty3dPalette.CargoDark, isLeftLimb = true)

    val rightLeg = Path().apply {
        moveTo(164f, 228f + bY * 0.4f)
        lineTo(176f, 280f + rightLegWalk * 0.5f)
        lineTo(174f, 350f + rightLegWalk)
        lineTo(208f, 350f + rightLegWalk)
        lineTo(210f, 276f + rightLegWalk * 0.5f)
        lineTo(198f, 224f + bY * 0.4f)
        close()
    }
    draw3dCylinder(rightLeg, Shorty3dPalette.CargoMid, Shorty3dPalette.CargoLight, Shorty3dPalette.CargoDark, isLeftLimb = false)

    // Chicago Red-and-White 3D High-Tops
    draw3dHighTopSneaker(
        x = 90f,
        y = 350f + leftLegWalk,
        width = 56f,
        height = 36f,
        primaryLight = Shorty3dPalette.CapRedLight,
        primaryMid = Shorty3dPalette.CapRedMid,
        primaryDark = Shorty3dPalette.CapRedDark,
        accentColor = Color(0xFFFFFFFF),
        facingRight = false
    )
    draw3dHighTopSneaker(
        x = 172f,
        y = 350f + rightLegWalk,
        width = 56f,
        height = 36f,
        primaryLight = Shorty3dPalette.CapRedLight,
        primaryMid = Shorty3dPalette.CapRedMid,
        primaryDark = Shorty3dPalette.CapRedDark,
        accentColor = Color(0xFFFFFFFF),
        facingRight = true
    )

    // 3. Volumetric Heavy Down Puffer Jacket (Rounded Baffles)
    val torsoY = 96f + bY

    // 3 Rounded Baffle Segments
    for (b in 0..2) {
        val byTop = torsoY + 16f + b * 36f
        val byH = 38f
        val baffle = Path().apply {
            moveTo(102f, byTop)
            cubicTo(120f, byTop - 4f, 200f, byTop - 4f, 218f, byTop)
            cubicTo(222f, byTop + byH * 0.5f, 220f, byTop + byH, 212f, byTop + byH)
            cubicTo(196f, byTop + byH + 3f, 124f, byTop + byH + 3f, 108f, byTop + byH)
            cubicTo(100f, byTop + byH, 98f, byTop + byH * 0.5f, 102f, byTop)
            close()
        }
        drawPath(
            baffle,
            brush = Brush.verticalGradient(
                colors = listOf(Shorty3dPalette.PufferLight, Shorty3dPalette.PufferRed, Shorty3dPalette.PufferShadow),
                startY = byTop,
                endY = byTop + byH
            ),
            style = Fill
        )
    }

    // Right Sleeve "OK" Shield Badge
    draw3dSphere(Offset(218f, torsoY + 52f), radius = 6.5f, baseColor = Color(0xFFFFFFFF), highlightColor = Color(0xFFFFFFFF), shadowColor = Color(0xFFCBD5E1))

    // Hands thrust deep into puffer side pockets (3D volume)
    val leftArm = Path().apply {
        moveTo(102f, torsoY + 16f)
        cubicTo(80f, torsoY + 60f, 95f, torsoY + 95f, 126f, torsoY + 104f)
        lineTo(116f, torsoY + 95f)
        lineTo(104f, torsoY + 65f)
        lineTo(116f, torsoY + 24f)
        close()
    }
    draw3dCylinder(leftArm, Shorty3dPalette.PufferRed, Shorty3dPalette.PufferLight, Shorty3dPalette.PufferShadow, isLeftLimb = true)

    val rightArm = Path().apply {
        moveTo(218f, torsoY + 16f)
        cubicTo(240f, torsoY + 60f, 225f, torsoY + 95f, 194f, torsoY + 104f)
        lineTo(204f, torsoY + 95f)
        lineTo(216f, torsoY + 65f)
        lineTo(204f, torsoY + 24f)
        close()
    }
    draw3dCylinder(rightArm, Shorty3dPalette.PufferRed, Shorty3dPalette.PufferLight, Shorty3dPalette.PufferShadow, isLeftLimb = false)

    // 4. Over-Ear 3D DJ Headphones around neck
    drawRoundRect(
        brush = Brush.horizontalGradient(
            colors = listOf(Hd3dPalettes.SilverMid, Hd3dPalettes.SilverLight, Hd3dPalettes.SilverDark),
            startX = 110f,
            endX = 210f
        ),
        topLeft = Offset(110f, torsoY + 8f),
        size = Size(100f, 7f),
        cornerRadius = CornerRadius(3f, 3f)
    )
    draw3dSphere(Offset(116f, torsoY + 18f), radius = 11f, baseColor = Shorty3dPalette.CapRedMid, highlightColor = Shorty3dPalette.CapRedLight, shadowColor = Shorty3dPalette.CapRedDark)
    draw3dSphere(Offset(204f, torsoY + 18f), radius = 11f, baseColor = Shorty3dPalette.CapRedMid, highlightColor = Shorty3dPalette.CapRedLight, shadowColor = Shorty3dPalette.CapRedDark)

    // 5. Fox Head, Backward Snapback & Smug Smirk
    val headY = 18f + bY

    // Pointed 3D Fox Ears (Black backing with white inner fur)
    val leftEar = Path().apply {
        moveTo(116f, headY + 28f)
        lineTo(92f, headY - 8f)
        lineTo(134f, headY + 12f)
        close()
    }
    drawPath(
        leftEar,
        brush = Brush.linearGradient(
            colors = listOf(Shorty3dPalette.FurMid, Shorty3dPalette.EarBlack),
            start = Offset(134f, headY + 12f),
            end = Offset(92f, headY - 8f)
        ),
        style = Fill
    )
    val leftEarInner = Path().apply {
        moveTo(114f, headY + 24f)
        lineTo(98f, headY)
        lineTo(128f, headY + 12f)
        close()
    }
    drawPath(leftEarInner, color = Shorty3dPalette.FurWhite, style = Fill)

    val rightEar = Path().apply {
        moveTo(204f, headY + 28f)
        lineTo(228f, headY - 8f)
        lineTo(186f, headY + 12f)
        close()
    }
    drawPath(
        rightEar,
        brush = Brush.linearGradient(
            colors = listOf(Shorty3dPalette.FurMid, Shorty3dPalette.EarBlack),
            start = Offset(186f, headY + 12f),
            end = Offset(228f, headY - 8f)
        ),
        style = Fill
    )
    val rightEarInner = Path().apply {
        moveTo(206f, headY + 24f)
        lineTo(222f, headY)
        lineTo(192f, headY + 12f)
        close()
    }
    drawPath(rightEarInner, color = Shorty3dPalette.FurWhite, style = Fill)

    // Backward Crimson Snapback Cap Crown
    val capCrown = Path().apply {
        moveTo(116f, headY + 30f)
        cubicTo(124f, headY + 4f, 196f, headY + 4f, 204f, headY + 30f)
        close()
    }
    drawPath(
        capCrown,
        brush = Brush.radialGradient(
            colors = listOf(Shorty3dPalette.CapRedLight, Shorty3dPalette.CapRedMid, Shorty3dPalette.CapRedDark),
            center = Offset(150f, headY + 12f),
            radius = 55f
        ),
        style = Fill
    )
    // Plastic Adjuster Strap Across Forehead
    drawRoundRect(
        color = Color(0xFF0F172A),
        topLeft = Offset(142f, headY + 26f),
        size = Size(36f, 5f),
        cornerRadius = CornerRadius(2f, 2f)
    )

    // Fox Head Shape (Volumetric Cheek Ruffs)
    val head = Path().apply {
        moveTo(114f, headY + 28f)
        lineTo(206f, headY + 28f)
        cubicTo(224f, headY + 50f, 220f, headY + 70f, 198f, headY + 84f)
        lineTo(160f, headY + 86f)
        lineTo(122f, headY + 84f)
        cubicTo(100f, headY + 70f, 96f, headY + 50f, 114f, headY + 28f)
        close()
    }
    drawPath(
        head,
        brush = Brush.radialGradient(
            colors = listOf(Shorty3dPalette.FurLight, Shorty3dPalette.FurMid, Shorty3dPalette.FurShadow),
            center = Offset(150f, headY + 45f),
            radius = 65f
        ),
        style = Fill
    )

    // White Cheek Ruffs & Snout
    val snout = Path().apply {
        moveTo(134f, headY + 52f)
        lineTo(186f, headY + 52f)
        cubicTo(180f, headY + 74f, 172f, headY + 84f, 160f, headY + 86f)
        cubicTo(148f, headY + 84f, 140f, headY + 74f, 134f, headY + 52f)
        close()
    }
    drawPath(
        snout,
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFFFFFFFF), Color(0xFFF1F5F9), Color(0xFFCBD5E1)),
            center = Offset(160f, headY + 65f),
            radius = 35f
        ),
        style = Fill
    )

    // Black 3D Button Nose & Smug Fox Smirk
    draw3dSphere(Offset(160f, headY + 62f), radius = 5f, baseColor = Color(0xFF1E293B), highlightColor = Color(0xFF475569), shadowColor = Color(0xFF05080E))
    val smirk = Path().apply {
        moveTo(154f, headY + 72f)
        cubicTo(160f, headY + 74f, 166f, headY + 73f, 172f, headY + 70f)
    }
    drawPath(smirk, color = Color(0xFF0F172A), style = Stroke(width = 2.2f, cap = StrokeCap.Round))

    // Emerald Green 3D Eyes
    if (!isBlinking) {
        draw3dEye(
            center = Offset(136f, headY + 44f),
            radius = 6.2f,
            irisColor = Shorty3dPalette.EyeGreen,
            irisGlowColor = Shorty3dPalette.EyeGreenGlow
        )
        draw3dEye(
            center = Offset(184f, headY + 44f),
            radius = 6.2f,
            irisColor = Shorty3dPalette.EyeGreen,
            irisGlowColor = Shorty3dPalette.EyeGreenGlow
        )
    }
}

fun DrawScope.drawShortyAvatarPortrait(isBlinking: Boolean) {
    // 320x320 64-Bit HD 3D Portrait View (Cap, Ears, Smirk, Over-Ear Headphones, Puffer)
    // Ears
    val leftEar = Path().apply { moveTo(95f, 90f); lineTo(45f, 15f); lineTo(125f, 55f); close() }
    drawPath(leftEar, brush = Brush.linearGradient(colors = listOf(Shorty3dPalette.FurMid, Shorty3dPalette.EarBlack), start = Offset(125f, 55f), end = Offset(45f, 15f)), style = Fill)
    val rightEar = Path().apply { moveTo(225f, 90f); lineTo(275f, 15f); lineTo(195f, 55f); close() }
    drawPath(rightEar, brush = Brush.linearGradient(colors = listOf(Shorty3dPalette.FurMid, Shorty3dPalette.EarBlack), start = Offset(195f, 55f), end = Offset(275f, 15f)), style = Fill)

    // Cap Crown
    val cap = Path().apply { moveTo(90f, 95f); cubicTo(110f, 25f, 210f, 25f, 230f, 95f); close() }
    drawPath(cap, brush = Brush.radialGradient(colors = listOf(Shorty3dPalette.CapRedLight, Shorty3dPalette.CapRedMid, Shorty3dPalette.CapRedDark), center = Offset(150f, 50f), radius = 100f), style = Fill)

    // Head Volume
    val head = Path().apply { moveTo(90f, 95f); lineTo(230f, 95f); lineTo(255f, 160f); lineTo(225f, 215f); lineTo(160f, 230f); lineTo(95f, 215f); lineTo(65f, 160f); close() }
    drawPath(head, brush = Brush.radialGradient(colors = listOf(Shorty3dPalette.FurLight, Shorty3dPalette.FurMid, Shorty3dPalette.FurShadow), center = Offset(150f, 130f), radius = 120f), style = Fill)

    // Snout
    val snout = Path().apply { moveTo(120f, 150f); lineTo(200f, 150f); cubicTo(190f, 190f, 180f, 225f, 160f, 230f); cubicTo(140f, 225f, 130f, 190f, 120f, 150f); close() }
    drawPath(snout, brush = Brush.radialGradient(colors = listOf(Color(0xFFFFFFFF), Color(0xFFF1F5F9), Color(0xFFCBD5E1)), center = Offset(160f, 175f), radius = 60f), style = Fill)
    draw3dSphere(Offset(160f, 170f), radius = 10f, baseColor = Color(0xFF1E293B), highlightColor = Color(0xFF475569), shadowColor = Color(0xFF05080E))

    // Smirk
    val smirk = Path().apply { moveTo(150f, 195f); cubicTo(160f, 200f, 172f, 198f, 182f, 190f) }
    drawPath(smirk, color = Color(0xFF0F172A), style = Stroke(width = 3.5f, cap = StrokeCap.Round))

    // Eyes
    if (!isBlinking) {
        draw3dEye(center = Offset(120f, 135f), radius = 13f, irisColor = Shorty3dPalette.EyeGreen, irisGlowColor = Shorty3dPalette.EyeGreenGlow)
        draw3dEye(center = Offset(200f, 135f), radius = 13f, irisColor = Shorty3dPalette.EyeGreen, irisGlowColor = Shorty3dPalette.EyeGreenGlow)
    }

    // Headphones around neck
    draw3dSphere(Offset(80f, 240f), radius = 24f, baseColor = Shorty3dPalette.CapRedMid, highlightColor = Shorty3dPalette.CapRedLight, shadowColor = Shorty3dPalette.CapRedDark)
    draw3dSphere(Offset(240f, 240f), radius = 24f, baseColor = Shorty3dPalette.CapRedMid, highlightColor = Shorty3dPalette.CapRedLight, shadowColor = Shorty3dPalette.CapRedDark)

    // Puffer Jacket
    val puffer = Path().apply { moveTo(40f, 320f); lineTo(280f, 320f); lineTo(250f, 240f); lineTo(70f, 240f); close() }
    drawPath(puffer, brush = Brush.radialGradient(colors = listOf(Shorty3dPalette.PufferLight, Shorty3dPalette.PufferRed, Shorty3dPalette.PufferShadow), center = Offset(160f, 270f), radius = 120f), style = Fill)
}
