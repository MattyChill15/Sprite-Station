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
 * Shayde — Covert Stealth Black Panther.
 * 64-Bit HD 3D Character Model (Star Fox 64 Style in High Definition).
 *
 * Sculpted 3D Volumetric Features:
 * - Sleek obsidian-black feline head with glossy coat sheen and pointed ears.
 * - Hypnotic luminous neon-violet vertical slit eyes with inner aura.
 * - Covert tactical earpiece with glowing neon-violet LED ring.
 * - Segmented carbon-fiber stealth armor suit with illuminated violet seams.
 * - Slender black 3D panther tail with cybernetic glowing violet nano-fiber ring.
 * - Stealth combat runners with glowing violet matrix laces.
 * - Soft underfoot ambient occlusion contact shadow.
 */
object Shayde3dPalette {
    val FurSheen = Color(0xFF334155)
    val FurObsidian = Color(0xFF1E293B)
    val FurShadow = Color(0xFF090D16)

    val VioletNeon = Color(0xFFA855F7)
    val VioletGlow = Color(0xFFD8B4FE)
    val VioletCore = Color(0xFF7E22CE)

    val ArmorCarbon = Color(0xFF0F172A)
    val ArmorSheen = Color(0xFF1E293B)
}

fun DrawScope.drawShaydeFullBody(
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

    // 1. Slender 3D Panther Tail with Glowing Violet Nano-Ring (Curving behind viewer's right)
    val tail = Path().apply {
        moveTo(178f, 250f + bY)
        cubicTo(230f, 230f, 270f, 280f, 250f, 350f)
        cubicTo(235f, 370f, 215f, 330f, 175f, 270f + bY)
        close()
    }
    drawPath(
        tail,
        brush = Brush.radialGradient(
            colors = listOf(Shayde3dPalette.FurSheen, Shayde3dPalette.FurObsidian, Shayde3dPalette.FurShadow),
            center = Offset(210f, 280f),
            radius = 70f
        ),
        style = Fill
    )
    // Cybernetic Glowing Violet Nano-Ring
    drawRoundRect(
        color = Shayde3dPalette.VioletNeon,
        topLeft = Offset(242f, 335f),
        size = Size(14f, 6f),
        cornerRadius = CornerRadius(2f, 2f)
    )

    // 2. Carbon-Fiber Stealth Armor Legs
    val leftLeg = Path().apply {
        moveTo(122f, 224f + bY * 0.4f)
        lineTo(110f, 276f + leftLegWalk * 0.5f)
        lineTo(112f, 350f + leftLegWalk)
        lineTo(146f, 350f + leftLegWalk)
        lineTo(144f, 280f + leftLegWalk * 0.5f)
        lineTo(158f, 228f + bY * 0.4f)
        close()
    }
    draw3dCylinder(leftLeg, Shayde3dPalette.ArmorCarbon, Shayde3dPalette.ArmorSheen, Shayde3dPalette.FurShadow, isLeftLimb = true)
    // Violet illuminated knee seam
    drawLine(Shayde3dPalette.VioletNeon, Offset(110f, 280f + leftLegWalk * 0.5f), Offset(144f, 280f + leftLegWalk * 0.5f), strokeWidth = 1.6f)

    val rightLeg = Path().apply {
        moveTo(164f, 228f + bY * 0.4f)
        lineTo(176f, 280f + rightLegWalk * 0.5f)
        lineTo(174f, 350f + rightLegWalk)
        lineTo(208f, 350f + rightLegWalk)
        lineTo(210f, 276f + rightLegWalk * 0.5f)
        lineTo(198f, 224f + bY * 0.4f)
        close()
    }
    draw3dCylinder(rightLeg, Shayde3dPalette.ArmorCarbon, Shayde3dPalette.ArmorSheen, Shayde3dPalette.FurShadow, isLeftLimb = false)
    // Violet illuminated knee seam
    drawLine(Shayde3dPalette.VioletNeon, Offset(176f, 280f + rightLegWalk * 0.5f), Offset(210f, 280f + rightLegWalk * 0.5f), strokeWidth = 1.6f)

    // Stealth Combat Runners
    draw3dHighTopSneaker(
        x = 90f,
        y = 350f + leftLegWalk,
        width = 56f,
        height = 36f,
        primaryLight = Shayde3dPalette.FurSheen,
        primaryMid = Shayde3dPalette.ArmorCarbon,
        primaryDark = Shayde3dPalette.FurShadow,
        accentColor = Shayde3dPalette.VioletNeon,
        facingRight = false
    )
    draw3dHighTopSneaker(
        x = 172f,
        y = 350f + rightLegWalk,
        width = 56f,
        height = 36f,
        primaryLight = Shayde3dPalette.FurSheen,
        primaryMid = Shayde3dPalette.ArmorCarbon,
        primaryDark = Shayde3dPalette.FurShadow,
        accentColor = Shayde3dPalette.VioletNeon,
        facingRight = true
    )

    // 3. Segmented Carbon-Fiber Stealth Armor Torso
    val torsoY = 96f + bY

    val torso = Path().apply {
        moveTo(102f, torsoY + 16f)
        cubicTo(120f, torsoY + 12f, 200f, torsoY + 12f, 218f, torsoY + 16f)
        lineTo(206f, torsoY + 130f)
        lineTo(114f, torsoY + 130f)
        close()
    }
    drawPath(
        torso,
        brush = Brush.radialGradient(
            colors = listOf(Shayde3dPalette.ArmorSheen, Shayde3dPalette.ArmorCarbon, Shayde3dPalette.FurShadow),
            center = Offset(150f, torsoY + 45f),
            radius = 110f
        ),
        style = Fill
    )

    // Illuminated Violet Seam Lines on Torso
    drawLine(Shayde3dPalette.VioletNeon, Offset(160f, torsoY + 16f), Offset(160f, torsoY + 126f), strokeWidth = 1.8f)
    drawLine(Shayde3dPalette.VioletNeon, Offset(130f, torsoY + 54f), Offset(190f, torsoY + 54f), strokeWidth = 1.8f)
    drawLine(Shayde3dPalette.VioletNeon, Offset(136f, torsoY + 86f), Offset(184f, torsoY + 86f), strokeWidth = 1.8f)

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
    draw3dCylinder(leftArm, Shayde3dPalette.ArmorCarbon, Shayde3dPalette.ArmorSheen, Shayde3dPalette.FurShadow, isLeftLimb = true)
    draw3dSphere(Offset(102f, torsoY + 116f), radius = 9f, baseColor = Shayde3dPalette.FurObsidian, highlightColor = Shayde3dPalette.FurSheen, shadowColor = Shayde3dPalette.FurShadow)

    val rightArm = Path().apply {
        moveTo(218f, torsoY + 16f)
        lineTo(240f, torsoY + 70f)
        lineTo(222f, torsoY + 112f)
        lineTo(204f, torsoY + 106f)
        lineTo(218f, torsoY + 70f)
        lineTo(204f, torsoY + 24f)
        close()
    }
    draw3dCylinder(rightArm, Shayde3dPalette.ArmorCarbon, Shayde3dPalette.ArmorSheen, Shayde3dPalette.FurShadow, isLeftLimb = false)
    draw3dSphere(Offset(216f, torsoY + 116f), radius = 9f, baseColor = Shayde3dPalette.FurObsidian, highlightColor = Shayde3dPalette.FurSheen, shadowColor = Shayde3dPalette.FurShadow)

    // 4. Obsidian Panther Head, Feline Ears & Hypnotic Violet Slit Eyes
    val headY = 18f + bY

    // Pointed Feline Ears
    val leftEar = Path().apply {
        moveTo(116f, headY + 28f)
        lineTo(96f, headY - 8f)
        lineTo(132f, headY + 12f)
        close()
    }
    drawPath(leftEar, color = Shayde3dPalette.FurObsidian, style = Fill)
    val leftEarInner = Path().apply {
        moveTo(114f, headY + 22f)
        lineTo(102f, headY)
        lineTo(126f, headY + 12f)
        close()
    }
    drawPath(leftEarInner, color = Shayde3dPalette.FurSheen, style = Fill)

    val rightEar = Path().apply {
        moveTo(204f, headY + 28f)
        lineTo(224f, headY - 8f)
        lineTo(188f, headY + 12f)
        close()
    }
    drawPath(rightEar, color = Shayde3dPalette.FurObsidian, style = Fill)
    val rightEarInner = Path().apply {
        moveTo(206f, headY + 22f)
        lineTo(218f, headY)
        lineTo(194f, headY + 12f)
        close()
    }
    drawPath(rightEarInner, color = Shayde3dPalette.FurSheen, style = Fill)

    // Panther Head Volume
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
            colors = listOf(Shayde3dPalette.FurSheen, Shayde3dPalette.FurObsidian, Shayde3dPalette.FurShadow),
            center = Offset(150f, headY + 45f),
            radius = 65f
        ),
        style = Fill
    )

    // Sleek Panther Muzzle & Nose
    val muzzle = Path().apply {
        moveTo(136f, headY + 54f)
        lineTo(184f, headY + 54f)
        cubicTo(180f, headY + 76f, 172f, headY + 84f, 160f, headY + 86f)
        cubicTo(148f, headY + 84f, 140f, headY + 76f, 136f, headY + 54f)
        close()
    }
    drawPath(
        muzzle,
        brush = Brush.radialGradient(
            colors = listOf(Shayde3dPalette.FurSheen, Shayde3dPalette.FurObsidian, Shayde3dPalette.FurShadow),
            center = Offset(160f, headY + 68f),
            radius = 35f
        ),
        style = Fill
    )
    draw3dSphere(Offset(160f, headY + 62f), radius = 5f, baseColor = Color(0xFF090D16), highlightColor = Color(0xFF1E293B), shadowColor = Color(0xFF020408))

    // Covert Tactical Earpiece with Pulsing Violet LED
    draw3dSphere(Offset(108f, headY + 36f), radius = 4f, baseColor = Shayde3dPalette.VioletNeon, highlightColor = Shayde3dPalette.VioletGlow, shadowColor = Shayde3dPalette.VioletCore)

    // Hypnotic Neon-Violet Vertical Slit Eyes
    if (!isBlinking) {
        draw3dEye(
            center = Offset(136f, headY + 46f),
            radius = 6.2f,
            irisColor = Shayde3dPalette.VioletNeon,
            irisGlowColor = Shayde3dPalette.VioletGlow,
            isSlitPupil = true,
            eyelinerColor = Color(0xFF020408)
        )
        draw3dEye(
            center = Offset(184f, headY + 46f),
            radius = 6.2f,
            irisColor = Shayde3dPalette.VioletNeon,
            irisGlowColor = Shayde3dPalette.VioletGlow,
            isSlitPupil = true,
            eyelinerColor = Color(0xFF020408)
        )
    }
}

fun DrawScope.drawShaydeAvatarPortrait(isBlinking: Boolean) {
    // 320x320 64-Bit HD 3D Portrait View (Obsidian Fur, Feline Ears, Neon-Violet Slit Eyes, Armor)
    val head = Path().apply { moveTo(90f, 90f); cubicTo(110f, 30f, 210f, 30f, 230f, 90f); lineTo(250f, 160f); lineTo(225f, 220f); lineTo(160f, 230f); lineTo(95f, 220f); lineTo(70f, 160f); close() }
    drawPath(head, brush = Brush.radialGradient(colors = listOf(Shayde3dPalette.FurSheen, Shayde3dPalette.FurObsidian, Shayde3dPalette.FurShadow), center = Offset(150f, 120f), radius = 120f), style = Fill)

    // Muzzle & Nose
    draw3dSphere(Offset(160f, 178f), radius = 32f, baseColor = Shayde3dPalette.FurObsidian, highlightColor = Shayde3dPalette.FurSheen, shadowColor = Shayde3dPalette.FurShadow)
    draw3dSphere(Offset(160f, 165f), radius = 11f, baseColor = Color(0xFF090D16), highlightColor = Color(0xFF1E293B), shadowColor = Color(0xFF020408))

    // Neon-Violet Slit Eyes
    if (!isBlinking) {
        draw3dEye(center = Offset(118f, 135f), radius = 13f, irisColor = Shayde3dPalette.VioletNeon, irisGlowColor = Shayde3dPalette.VioletGlow, isSlitPupil = true, eyelinerColor = Color(0xFF020408))
        draw3dEye(center = Offset(202f, 135f), radius = 13f, irisColor = Shayde3dPalette.VioletNeon, irisGlowColor = Shayde3dPalette.VioletGlow, isSlitPupil = true, eyelinerColor = Color(0xFF020408))
    }

    // Stealth Armor
    val armor = Path().apply { moveTo(40f, 320f); lineTo(280f, 320f); lineTo(250f, 245f); lineTo(70f, 245f); close() }
    drawPath(armor, brush = Brush.radialGradient(colors = listOf(Shayde3dPalette.ArmorSheen, Shayde3dPalette.ArmorCarbon, Shayde3dPalette.FurShadow), center = Offset(160f, 275f), radius = 120f), style = Fill)
    drawLine(Shayde3dPalette.VioletNeon, Offset(160f, 245f), Offset(160f, 320f), strokeWidth = 3.5f)
}
