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
 * Affleck — Boston Terrier Tactical Enforcer.
 * 64-Bit HD 3D Character Model (Star Fox 64 Style in High Definition).
 *
 * Sculpted 3D Volumetric Features:
 * - Crisp black-and-white tuxedo blaze running up between tall erect 3D bat ears with pinkish inner ear contours.
 * - Unblinking bulldog stare with intense dark pupils.
 * - Solid silver 3D Cuban curb-link chain necklace with interlocking metallic specular highlights.
 * - Heather grey fleece pullover 3D hoodie with front kangaroo pouch and white drawstring aglets.
 * - Raw dark indigo denim jeans with 3D cylindrical legs and natural folds.
 * - Black-and-white skate sneakers with rounded rubber toe caps.
 * - Soft underfoot ambient occlusion contact shadow.
 */
object Affleck3dPalette {
    val CoatBlack = Color(0xFF1E293B)
    val CoatShadow = Color(0xFF0F172A)
    val CoatWhite = Color(0xFFF8FAFC)
    val InnerEarPink = Color(0xFFFDA4AF)

    val ChainLight = Color(0xFFFFFFFF)
    val ChainSilver = Color(0xFFCBD5E1)
    val ChainShadow = Color(0xFF64748B)

    val HoodieLight = Color(0xFFE2E8F0)
    val HoodieGrey = Color(0xFF94A3B8)
    val HoodieShadow = Color(0xFF64748B)

    val DenimLight = Color(0xFF334155)
    val DenimBlue = Color(0xFF1E293B)
    val DenimShadow = Color(0xFF0F172A)
}

fun DrawScope.drawAffleckFullBody(
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

    // 1. Raw Indigo Denim Jeans
    val leftLeg = Path().apply {
        moveTo(122f, 224f + bY * 0.4f)
        lineTo(110f, 276f + leftLegWalk * 0.5f)
        lineTo(112f, 350f + leftLegWalk)
        lineTo(146f, 350f + leftLegWalk)
        lineTo(144f, 280f + leftLegWalk * 0.5f)
        lineTo(158f, 228f + bY * 0.4f)
        close()
    }
    draw3dCylinder(leftLeg, Affleck3dPalette.DenimBlue, Affleck3dPalette.DenimLight, Affleck3dPalette.DenimShadow, isLeftLimb = true)

    val rightLeg = Path().apply {
        moveTo(164f, 228f + bY * 0.4f)
        lineTo(176f, 280f + rightLegWalk * 0.5f)
        lineTo(174f, 350f + rightLegWalk)
        lineTo(208f, 350f + rightLegWalk)
        lineTo(210f, 276f + rightLegWalk * 0.5f)
        lineTo(198f, 224f + bY * 0.4f)
        close()
    }
    draw3dCylinder(rightLeg, Affleck3dPalette.DenimBlue, Affleck3dPalette.DenimLight, Affleck3dPalette.DenimShadow, isLeftLimb = false)

    // Black-and-White Skate Sneakers
    draw3dHighTopSneaker(
        x = 90f,
        y = 350f + leftLegWalk,
        width = 56f,
        height = 36f,
        primaryLight = Color(0xFF334155),
        primaryMid = Color(0xFF1E293B),
        primaryDark = Color(0xFF0F172A),
        accentColor = Color(0xFFFFFFFF),
        facingRight = false
    )
    draw3dHighTopSneaker(
        x = 172f,
        y = 350f + rightLegWalk,
        width = 56f,
        height = 36f,
        primaryLight = Color(0xFF334155),
        primaryMid = Color(0xFF1E293B),
        primaryDark = Color(0xFF0F172A),
        accentColor = Color(0xFFFFFFFF),
        facingRight = true
    )

    // 2. Heather Grey Fleece Pullover Hoodie & Silver Cuban Chain
    val torsoY = 96f + bY

    // Hoodie Body
    val hoodie = Path().apply {
        moveTo(102f, torsoY + 16f)
        cubicTo(120f, torsoY + 12f, 200f, torsoY + 12f, 218f, torsoY + 16f)
        lineTo(206f, torsoY + 130f)
        lineTo(114f, torsoY + 130f)
        close()
    }
    drawPath(
        hoodie,
        brush = Brush.radialGradient(
            colors = listOf(Affleck3dPalette.HoodieLight, Affleck3dPalette.HoodieGrey, Affleck3dPalette.HoodieShadow),
            center = Offset(150f, torsoY + 45f),
            radius = 110f
        ),
        style = Fill
    )

    // Front Kangaroo Pouch
    val pouch = Path().apply {
        moveTo(126f, torsoY + 74f)
        lineTo(194f, torsoY + 74f)
        lineTo(200f, torsoY + 122f)
        lineTo(120f, torsoY + 122f)
        close()
    }
    drawPath(
        pouch,
        brush = Brush.verticalGradient(
            colors = listOf(Affleck3dPalette.HoodieLight, Affleck3dPalette.HoodieGrey),
            startY = torsoY + 74f,
            endY = torsoY + 122f
        ),
        style = Fill
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
    draw3dCylinder(leftArm, Affleck3dPalette.HoodieGrey, Affleck3dPalette.HoodieLight, Affleck3dPalette.HoodieShadow, isLeftLimb = true)
    draw3dSphere(Offset(102f, torsoY + 116f), radius = 9f, baseColor = Color(0xFFFFFFFF), highlightColor = Color(0xFFFFFFFF), shadowColor = Color(0xFFCBD5E1))

    val rightArm = Path().apply {
        moveTo(218f, torsoY + 16f)
        lineTo(240f, torsoY + 70f)
        lineTo(222f, torsoY + 112f)
        lineTo(204f, torsoY + 106f)
        lineTo(218f, torsoY + 70f)
        lineTo(204f, torsoY + 24f)
        close()
    }
    draw3dCylinder(rightArm, Affleck3dPalette.HoodieGrey, Affleck3dPalette.HoodieLight, Affleck3dPalette.HoodieShadow, isLeftLimb = false)
    draw3dSphere(Offset(216f, torsoY + 116f), radius = 9f, baseColor = Color(0xFFFFFFFF), highlightColor = Color(0xFFFFFFFF), shadowColor = Color(0xFFCBD5E1))

    // Solid Silver 3D Cuban Chain Necklace
    for (i in 0..6) {
        val cx = 132f + i * 9.5f
        val cy = torsoY + 22f + (i - 3) * (i - 3) * 1.5f
        draw3dSphere(Offset(cx, cy), radius = 4f, baseColor = Affleck3dPalette.ChainSilver, highlightColor = Affleck3dPalette.ChainLight, shadowColor = Affleck3dPalette.ChainShadow)
    }

    // 3. Boston Terrier Head, Tuxedo Markings & Bat Ears
    val headY = 18f + bY

    // Tall Erect 3D Bat Ears
    val leftEar = Path().apply {
        moveTo(116f, headY + 26f)
        cubicTo(100f, headY, 95f, headY - 18f, 102f, headY - 26f)
        cubicTo(112f, headY - 26f, 126f, headY, 134f, headY + 16f)
        close()
    }
    drawPath(leftEar, color = Affleck3dPalette.CoatBlack, style = Fill)
    val leftEarInner = Path().apply {
        moveTo(112f, headY + 20f)
        cubicTo(104f, headY + 2f, 102f, headY - 14f, 106f, headY - 18f)
        cubicTo(112f, headY - 18f, 120f, headY + 2f, 124f, headY + 14f)
        close()
    }
    drawPath(leftEarInner, color = Affleck3dPalette.InnerEarPink, style = Fill)

    val rightEar = Path().apply {
        moveTo(204f, headY + 26f)
        cubicTo(220f, headY, 225f, headY - 18f, 218f, headY - 26f)
        cubicTo(208f, headY - 26f, 194f, headY, 186f, headY + 16f)
        close()
    }
    drawPath(rightEar, color = Affleck3dPalette.CoatBlack, style = Fill)
    val rightEarInner = Path().apply {
        moveTo(208f, headY + 20f)
        cubicTo(216f, headY + 2f, 218f, headY - 14f, 214f, headY - 18f)
        cubicTo(208f, headY - 18f, 200f, headY + 2f, 196f, headY + 14f)
        close()
    }
    drawPath(rightEarInner, color = Affleck3dPalette.InnerEarPink, style = Fill)

    // Square-Rounded Head Silhouette (Black Mask sides)
    val head = Path().apply {
        moveTo(114f, headY + 26f)
        lineTo(206f, headY + 26f)
        cubicTo(220f, headY + 50f, 218f, headY + 76f, 204f, headY + 86f)
        lineTo(160f, headY + 88f)
        lineTo(116f, headY + 86f)
        cubicTo(102f, headY + 76f, 100f, headY + 50f, 114f, headY + 26f)
        close()
    }
    drawPath(
        head,
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFF334155), Affleck3dPalette.CoatBlack, Affleck3dPalette.CoatShadow),
            center = Offset(150f, headY + 40f),
            radius = 65f
        ),
        style = Fill
    )

    // Crisp Center White Tuxedo Blaze
    val blaze = Path().apply {
        moveTo(154f, headY + 24f)
        lineTo(166f, headY + 24f)
        lineTo(172f, headY + 50f)
        cubicTo(184f, headY + 68f, 182f, headY + 86f, 160f, headY + 88f)
        cubicTo(138f, headY + 86f, 136f, headY + 68f, 148f, headY + 50f)
        close()
    }
    drawPath(
        blaze,
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFFFFFFFF), Color(0xFFF1F5F9), Color(0xFFCBD5E1)),
            center = Offset(160f, headY + 60f),
            radius = 35f
        ),
        style = Fill
    )

    // Broad Bulldog Muzzle & Black Button Nose
    draw3dSphere(Offset(160f, headY + 64f), radius = 6f, baseColor = Color(0xFF1E293B), highlightColor = Color(0xFF475569), shadowColor = Color(0xFF05080E))

    // Unblinking Bulldog Stare Eyes
    if (!isBlinking) {
        draw3dEye(center = Offset(134f, headY + 46f), radius = 6.8f, irisColor = Color(0xFF78350F), irisGlowColor = Color(0xFFB45309))
        draw3dEye(center = Offset(186f, headY + 46f), radius = 6.8f, irisColor = Color(0xFF78350F), irisGlowColor = Color(0xFFB45309))
    }
}

fun DrawScope.drawAffleckAvatarPortrait(isBlinking: Boolean) {
    // 320x320 64-Bit HD 3D Portrait View (Bat Ears, Tuxedo Markings, Silver Cuban Chain, Hoodie)
    // Bat Ears
    val leftEar = Path().apply { moveTo(95f, 90f); cubicTo(70f, 30f, 60f, -10f, 75f, -25f); cubicTo(95f, -25f, 120f, 30f, 130f, 65f); close() }
    drawPath(leftEar, color = Affleck3dPalette.CoatBlack, style = Fill)
    val rightEar = Path().apply { moveTo(225f, 90f); cubicTo(250f, 30f, 260f, -10f, 245f, -25f); cubicTo(225f, -25f, 200f, 30f, 190f, 65f); close() }
    drawPath(rightEar, color = Affleck3dPalette.CoatBlack, style = Fill)

    // Head Volume
    val head = Path().apply { moveTo(90f, 90f); lineTo(230f, 90f); lineTo(250f, 160f); lineTo(225f, 220f); lineTo(160f, 230f); lineTo(95f, 220f); lineTo(70f, 160f); close() }
    drawPath(head, brush = Brush.radialGradient(colors = listOf(Color(0xFF334155), Affleck3dPalette.CoatBlack, Affleck3dPalette.CoatShadow), center = Offset(150f, 120f), radius = 120f), style = Fill)

    // White Tuxedo Blaze
    val blaze = Path().apply { moveTo(150f, 90f); lineTo(170f, 90f); lineTo(185f, 140f); cubicTo(210f, 175f, 205f, 225f, 160f, 230f); cubicTo(115f, 225f, 110f, 175f, 135f, 140f); close() }
    drawPath(blaze, brush = Brush.radialGradient(colors = listOf(Color(0xFFFFFFFF), Color(0xFFF1F5F9), Color(0xFFCBD5E1)), center = Offset(160f, 170f), radius = 70f), style = Fill)
    draw3dSphere(Offset(160f, 170f), radius = 12f, baseColor = Color(0xFF1E293B), highlightColor = Color(0xFF475569), shadowColor = Color(0xFF05080E))

    // Eyes
    if (!isBlinking) {
        draw3dEye(center = Offset(118f, 136f), radius = 14f, irisColor = Color(0xFF78350F), irisGlowColor = Color(0xFFB45309))
        draw3dEye(center = Offset(202f, 136f), radius = 14f, irisColor = Color(0xFF78350F), irisGlowColor = Color(0xFFB45309))
    }

    // Heavy Silver Cuban Chain
    for (i in 0..7) {
        val cx = 100f + i * 17f
        val cy = 230f + (i - 3.5f) * (i - 3.5f) * 2.5f
        draw3dSphere(Offset(cx, cy), radius = 8.5f, baseColor = Affleck3dPalette.ChainSilver, highlightColor = Affleck3dPalette.ChainLight, shadowColor = Affleck3dPalette.ChainShadow)
    }

    // Hoodie
    val hoodie = Path().apply { moveTo(40f, 320f); lineTo(280f, 320f); lineTo(250f, 245f); lineTo(70f, 245f); close() }
    drawPath(hoodie, brush = Brush.radialGradient(colors = listOf(Affleck3dPalette.HoodieLight, Affleck3dPalette.HoodieGrey, Affleck3dPalette.HoodieShadow), center = Offset(160f, 275f), radius = 120f), style = Fill)
}
