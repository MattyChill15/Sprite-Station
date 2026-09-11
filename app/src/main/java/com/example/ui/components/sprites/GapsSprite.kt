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
 * Gaps — Great Horned Owl Auditor.
 * 64-Bit HD 3D Character Model (Star Fox 64 Style in High Definition).
 *
 * Sculpted 3D Volumetric Features:
 * - Prominent 3D feathered ear horns/tufts with layered feather depth.
 * - Circular buff facial disc with dark scalloped border, sharp hooked golden beak.
 * - Staring wide 3D amber owl eyes with glowing pupils.
 * - Gold wire-frame reading spectacles perched on beak with glass reflections.
 * - Olive-drab military auditor uniform with silver rank collar tabs and star badge.
 * - Left wing holding 3D wooden clipboard with silver clamp and checklist with green checkmarks.
 * - Polished black officer dress oxfords with patent leather shine.
 * - Soft underfoot ambient occlusion contact shadow.
 */
object Gaps3dPalette {
    val FeatherBuffLight = Color(0xFFFEF3C7)
    val FeatherBuff = Color(0xFFFDE68A)
    val FeatherBrown = Color(0xFFB45309)
    val FeatherDark = Color(0xFF78350F)

    val BeakGold = Color(0xFFF59E0B)
    val BeakShadow = Color(0xFFB45309)

    val SpectacleGold = Color(0xFFFACC15)
    val SpectacleGlass = Color(0x88E0F2FE)

    val EyeAmber = Color(0xFFF59E0B)
    val EyeGlow = Color(0xFFFEF08A)

    val UniformOliveLight = Color(0xFF65A30D)
    val UniformOlive = Color(0xFF4D7C0F)
    val UniformOliveDark = Color(0xFF365314)

    val ClipboardWood = Color(0xFF92400E)
    val CheckmarkGreen = Color(0xFF10B981)
}

fun DrawScope.drawGapsFullBody(
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

    // 1. Uniform Trousers Legs
    val leftLeg = Path().apply {
        moveTo(122f, 224f + bY * 0.4f)
        lineTo(110f, 276f + leftLegWalk * 0.5f)
        lineTo(112f, 350f + leftLegWalk)
        lineTo(146f, 350f + leftLegWalk)
        lineTo(144f, 280f + leftLegWalk * 0.5f)
        lineTo(158f, 228f + bY * 0.4f)
        close()
    }
    draw3dCylinder(leftLeg, Gaps3dPalette.UniformOlive, Gaps3dPalette.UniformOliveLight, Gaps3dPalette.UniformOliveDark, isLeftLimb = true)

    val rightLeg = Path().apply {
        moveTo(164f, 228f + bY * 0.4f)
        lineTo(176f, 280f + rightLegWalk * 0.5f)
        lineTo(174f, 350f + rightLegWalk)
        lineTo(208f, 350f + rightLegWalk)
        lineTo(210f, 276f + rightLegWalk * 0.5f)
        lineTo(198f, 224f + bY * 0.4f)
        close()
    }
    draw3dCylinder(rightLeg, Gaps3dPalette.UniformOlive, Gaps3dPalette.UniformOliveLight, Gaps3dPalette.UniformOliveDark, isLeftLimb = false)

    // Polished Black Officer Dress Oxfords
    draw3dCombatBoot(
        x = 90f,
        y = 350f + leftLegWalk,
        width = 56f,
        height = 36f,
        leatherLight = Color(0xFF475569),
        leatherMid = Color(0xFF1E293B),
        leatherDark = Color(0xFF0F172A),
        facingRight = false
    )
    draw3dCombatBoot(
        x = 172f,
        y = 350f + rightLegWalk,
        width = 56f,
        height = 36f,
        leatherLight = Color(0xFF475569),
        leatherMid = Color(0xFF1E293B),
        leatherDark = Color(0xFF0F172A),
        facingRight = true
    )

    // 2. Olive Military Auditor Uniform & Clipboard
    val torsoY = 96f + bY

    val uniform = Path().apply {
        moveTo(102f, torsoY + 16f)
        cubicTo(120f, torsoY + 12f, 200f, torsoY + 12f, 218f, torsoY + 16f)
        lineTo(206f, torsoY + 130f)
        lineTo(114f, torsoY + 130f)
        close()
    }
    drawPath(
        uniform,
        brush = Brush.radialGradient(
            colors = listOf(Gaps3dPalette.UniformOliveLight, Gaps3dPalette.UniformOlive, Gaps3dPalette.UniformOliveDark),
            center = Offset(150f, torsoY + 45f),
            radius = 110f
        ),
        style = Fill
    )

    // Silver Rank Tabs & Star Badge
    draw3dSphere(Offset(136f, torsoY + 48f), radius = 6f, baseColor = Hd3dPalettes.SilverMid, highlightColor = Hd3dPalettes.SilverLight, shadowColor = Hd3dPalettes.SilverDark)

    // Right Arm (Wing)
    val rightArm = Path().apply {
        moveTo(218f, torsoY + 16f)
        lineTo(240f, torsoY + 70f)
        lineTo(222f, torsoY + 112f)
        lineTo(204f, torsoY + 106f)
        lineTo(218f, torsoY + 70f)
        lineTo(204f, torsoY + 24f)
        close()
    }
    draw3dCylinder(rightArm, Gaps3dPalette.UniformOlive, Gaps3dPalette.UniformOliveLight, Gaps3dPalette.UniformOliveDark, isLeftLimb = false)

    // Left Arm Holding 3D Wooden Audit Clipboard
    val leftArm = Path().apply {
        moveTo(102f, torsoY + 16f)
        lineTo(76f, torsoY + 65f)
        lineTo(96f, torsoY + 108f)
        lineTo(114f, torsoY + 102f)
        lineTo(100f, torsoY + 65f)
        lineTo(114f, torsoY + 24f)
        close()
    }
    draw3dCylinder(leftArm, Gaps3dPalette.UniformOlive, Gaps3dPalette.UniformOliveLight, Gaps3dPalette.UniformOliveDark, isLeftLimb = true)

    // 3D Wooden Audit Clipboard
    val clipX = 74f
    val clipY = torsoY + 60f
    drawRoundRect(
        brush = Brush.verticalGradient(
            colors = listOf(Color(0xFFB45309), Gaps3dPalette.ClipboardWood, Color(0xFF451A03)),
            startY = clipY,
            endY = clipY + 46f
        ),
        topLeft = Offset(clipX, clipY),
        size = Size(28f, 46f),
        cornerRadius = CornerRadius(3f, 3f)
    )
    // White Paper Sheet
    drawRoundRect(
        color = Color(0xFFF8FAFC),
        topLeft = Offset(clipX + 3f, clipY + 5f),
        size = Size(22f, 36f),
        cornerRadius = CornerRadius(2f, 2f)
    )
    // Silver Spring Clamp
    drawRoundRect(
        brush = Brush.horizontalGradient(colors = listOf(Hd3dPalettes.SilverDark, Hd3dPalettes.SilverLight, Hd3dPalettes.SilverDark), startX = clipX + 6f, endX = clipX + 22f),
        topLeft = Offset(clipX + 6f, clipY + 2f),
        size = Size(16f, 6f),
        cornerRadius = CornerRadius(1.5f, 1.5f)
    )
    // Green Checkmarks
    for (c in 0..2) {
        val cy = clipY + 12f + c * 8f
        drawLine(Gaps3dPalette.CheckmarkGreen, Offset(clipX + 6f, cy + 2f), Offset(clipX + 9f, cy + 5f), strokeWidth = 2f)
        drawLine(Gaps3dPalette.CheckmarkGreen, Offset(clipX + 9f, cy + 5f), Offset(clipX + 15f, cy), strokeWidth = 2f)
    }

    // 3. Owl Head, Ear Horns, Facial Disc & Spectacles
    val headY = 18f + bY

    // Prominent 3D Ear Horns / Feather Tufts
    val leftTuft = Path().apply {
        moveTo(116f, headY + 24f)
        lineTo(96f, headY - 18f)
        lineTo(128f, headY + 10f)
        close()
    }
    drawPath(
        leftTuft,
        brush = Brush.linearGradient(
            colors = listOf(Gaps3dPalette.FeatherDark, Gaps3dPalette.FeatherBrown),
            start = Offset(96f, headY - 18f),
            end = Offset(128f, headY + 10f)
        ),
        style = Fill
    )

    val rightTuft = Path().apply {
        moveTo(204f, headY + 24f)
        lineTo(224f, headY - 18f)
        lineTo(192f, headY + 10f)
        close()
    }
    drawPath(
        rightTuft,
        brush = Brush.linearGradient(
            colors = listOf(Gaps3dPalette.FeatherDark, Gaps3dPalette.FeatherBrown),
            start = Offset(224f, headY - 18f),
            end = Offset(192f, headY + 10f)
        ),
        style = Fill
    )

    // Owl Head Volume
    val head = Path().apply {
        moveTo(114f, headY + 24f)
        cubicTo(124f, headY + 4f, 196f, headY + 4f, 206f, headY + 24f)
        cubicTo(222f, headY + 48f, 220f, headY + 74f, 202f, headY + 86f)
        lineTo(160f, headY + 88f)
        lineTo(118f, headY + 86f)
        cubicTo(100f, headY + 74f, 98f, headY + 48f, 114f, headY + 24f)
        close()
    }
    drawPath(
        head,
        brush = Brush.radialGradient(
            colors = listOf(Gaps3dPalette.FeatherBuff, Gaps3dPalette.FeatherBrown, Gaps3dPalette.FeatherDark),
            center = Offset(150f, headY + 40f),
            radius = 65f
        ),
        style = Fill
    )

    // Circular Buff Facial Disc
    val disc = Path().apply {
        moveTo(122f, headY + 36f)
        cubicTo(134f, headY + 26f, 186f, headY + 26f, 198f, headY + 36f)
        cubicTo(210f, headY + 54f, 208f, headY + 76f, 194f, headY + 84f)
        lineTo(160f, headY + 86f)
        lineTo(126f, headY + 84f)
        cubicTo(112f, headY + 76f, 110f, headY + 54f, 122f, headY + 36f)
        close()
    }
    drawPath(
        disc,
        brush = Brush.radialGradient(
            colors = listOf(Gaps3dPalette.FeatherBuffLight, Gaps3dPalette.FeatherBuff, Gaps3dPalette.FeatherBrown),
            center = Offset(160f, headY + 54f),
            radius = 45f
        ),
        style = Fill
    )

    // Curved Golden Raptor Beak
    val beak = Path().apply {
        moveTo(152f, headY + 52f)
        lineTo(168f, headY + 52f)
        cubicTo(170f, headY + 66f, 166f, headY + 78f, 160f, headY + 82f)
        cubicTo(154f, headY + 78f, 150f, headY + 66f, 152f, headY + 52f)
        close()
    }
    drawPath(
        beak,
        brush = Brush.radialGradient(
            colors = listOf(Gaps3dPalette.SpectacleGold, Gaps3dPalette.BeakGold, Gaps3dPalette.BeakShadow),
            center = Offset(160f, headY + 64f),
            radius = 20f
        ),
        style = Fill
    )

    // Staring Wide Amber Owl Eyes
    if (!isBlinking) {
        draw3dEye(center = Offset(136f, headY + 48f), radius = 7f, irisColor = Gaps3dPalette.EyeAmber, irisGlowColor = Gaps3dPalette.EyeGlow)
        draw3dEye(center = Offset(184f, headY + 48f), radius = 7f, irisColor = Gaps3dPalette.EyeAmber, irisGlowColor = Gaps3dPalette.EyeGlow)
    }

    // Gold Wire-Frame Reading Spectacles Perched on Beak
    drawCircle(color = Gaps3dPalette.SpectacleGold, radius = 9.5f, center = Offset(136f, headY + 48f), style = Stroke(width = 1.8f))
    drawCircle(color = Gaps3dPalette.SpectacleGold, radius = 9.5f, center = Offset(184f, headY + 48f), style = Stroke(width = 1.8f))
    drawLine(Gaps3dPalette.SpectacleGold, Offset(145f, headY + 48f), Offset(175f, headY + 48f), strokeWidth = 1.8f)
    // Glass Glare
    drawLine(Gaps3dPalette.SpectacleGlass, Offset(132f, headY + 44f), Offset(140f, headY + 52f), strokeWidth = 1.5f)
    drawLine(Gaps3dPalette.SpectacleGlass, Offset(180f, headY + 44f), Offset(188f, headY + 52f), strokeWidth = 1.5f)
}

fun DrawScope.drawGapsAvatarPortrait(isBlinking: Boolean) {
    // 320x320 64-Bit HD 3D Portrait View (Tufts, Facial Disc, Gold Wire Spectacles, Beak)
    // Ear Tufts
    val leftTuft = Path().apply { moveTo(95f, 80f); lineTo(45f, -15f); lineTo(130f, 45f); close() }
    drawPath(leftTuft, brush = Brush.linearGradient(colors = listOf(Gaps3dPalette.FeatherDark, Gaps3dPalette.FeatherBrown), start = Offset(45f, -15f), end = Offset(130f, 45f)), style = Fill)
    val rightTuft = Path().apply { moveTo(225f, 80f); lineTo(275f, -15f); lineTo(190f, 45f); close() }
    drawPath(rightTuft, brush = Brush.linearGradient(colors = listOf(Gaps3dPalette.FeatherDark, Gaps3dPalette.FeatherBrown), start = Offset(275f, -15f), end = Offset(190f, 45f)), style = Fill)

    // Head Volume
    val head = Path().apply { moveTo(90f, 85f); cubicTo(110f, 25f, 210f, 25f, 230f, 85f); lineTo(255f, 160f); lineTo(225f, 225f); lineTo(160f, 235f); lineTo(95f, 225f); lineTo(65f, 160f); close() }
    drawPath(head, brush = Brush.radialGradient(colors = listOf(Gaps3dPalette.FeatherBuff, Gaps3dPalette.FeatherBrown, Gaps3dPalette.FeatherDark), center = Offset(150f, 120f), radius = 125f), style = Fill)

    // Facial Disc
    val disc = Path().apply { moveTo(105f, 110f); cubicTo(125f, 85f, 195f, 85f, 215f, 110f); cubicTo(235f, 160f, 225f, 210f, 160f, 225f); cubicTo(95f, 210f, 85f, 160f, 105f, 110f); close() }
    drawPath(disc, brush = Brush.radialGradient(colors = listOf(Gaps3dPalette.FeatherBuffLight, Gaps3dPalette.FeatherBuff, Gaps3dPalette.FeatherBrown), center = Offset(160f, 150f), radius = 80f), style = Fill)

    // Golden Hooked Beak
    val beak = Path().apply { moveTo(142f, 145f); lineTo(178f, 145f); cubicTo(185f, 185f, 175f, 220f, 160f, 225f); cubicTo(145f, 220f, 135f, 185f, 142f, 145f); close() }
    drawPath(beak, brush = Brush.radialGradient(colors = listOf(Gaps3dPalette.SpectacleGold, Gaps3dPalette.BeakGold, Gaps3dPalette.BeakShadow), center = Offset(160f, 175f), radius = 45f), style = Fill)

    // Eyes
    if (!isBlinking) {
        draw3dEye(center = Offset(118f, 135f), radius = 16f, irisColor = Gaps3dPalette.EyeAmber, irisGlowColor = Gaps3dPalette.EyeGlow)
        draw3dEye(center = Offset(202f, 135f), radius = 16f, irisColor = Gaps3dPalette.EyeAmber, irisGlowColor = Gaps3dPalette.EyeGlow)
    }

    // Gold Wire Spectacles
    drawCircle(color = Gaps3dPalette.SpectacleGold, radius = 22f, center = Offset(118f, 135f), style = Stroke(width = 3.5f))
    drawCircle(color = Gaps3dPalette.SpectacleGold, radius = 22f, center = Offset(202f, 135f), style = Stroke(width = 3.5f))
    drawLine(Gaps3dPalette.SpectacleGold, Offset(140f, 135f), Offset(180f, 135f), strokeWidth = 3.5f)

    // Uniform
    val uniform = Path().apply { moveTo(40f, 320f); lineTo(280f, 320f); lineTo(250f, 245f); lineTo(70f, 245f); close() }
    drawPath(uniform, brush = Brush.radialGradient(colors = listOf(Gaps3dPalette.UniformOliveLight, Gaps3dPalette.UniformOlive, Gaps3dPalette.UniformOliveDark), center = Offset(160f, 275f), radius = 120f), style = Fill)
}
