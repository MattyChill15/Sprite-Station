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
 * Chief — Commander Bald Eagle.
 * 64-Bit HD 3D Character Model (Star Fox 64 Style in High Definition).
 *
 * Sculpted 3D Volumetric Features:
 * - 3D predatory eagle skull with smooth white plumage gradients and crest depth.
 * - Razor golden hooked raptor beak with 3D surface shading, specular ridge, and curved tip.
 * - Piercing 3D amber eagle eyes with dual specular glints and deep orbital brow ridge.
 * - Royal blue 3D flight jumpsuit with cylindrical limb shading, brass metallic center zipper,
 *   3D gold commander epaulets, and webbed flight harness.
 * - Left talon clenched in white feathered fist; right hand holding glowing 3D tactical command smartphone.
 * - Sculpted royal blue 3D retro high-top basketball sneakers with white leather overlays.
 * - Soft underfoot ambient occlusion contact shadow.
 */
object Chief3dPalette {
    val FeatherLight = Color(0xFFFFFFFF)
    val FeatherMid = Color(0xFFF1F5F9)
    val FeatherShadow = Color(0xFFCBD5E1)
    val FeatherDeep = Color(0xFF94A3B8)

    val BeakLight = Color(0xFFFEF08A)
    val BeakGold = Color(0xFFF59E0B)
    val BeakShadow = Color(0xFFB45309)

    val EyeGold = Color(0xFFF59E0B)
    val EyeGlow = Color(0xFFFDE047)

    val SuitLight = Color(0xFF3B82F6)
    val SuitMid = Color(0xFF1D4ED8)
    val SuitShadow = Color(0xFF1E3A8A)
    val SuitDeep = Color(0xFF172554)

    val HarnessDark = Color(0xFF0F172A)
    val PhoneGreen = Color(0xFF10B981)
    val PhoneGlow = Color(0xFF34D399)
}

fun DrawScope.drawChiefFullBody(
    breatheY: Float,
    walkOffset: Float,
    isWalking: Boolean,
    isBlinking: Boolean
) {
    val bY = breatheY
    val leftLegWalk = walkOffset
    val rightLegWalk = -walkOffset

    // 0. Soft 3D Ground Contact Shadow
    draw3dContactShadow(160f, 382f, radiusX = 66f, radiusY = 14f)

    // 1. Volumetric 3D Legs (Cylindrical form with light on left, shadow on right)
    val leftLeg = Path().apply {
        moveTo(122f, 224f + bY * 0.4f)
        lineTo(110f, 276f + leftLegWalk * 0.5f)
        lineTo(112f, 350f + leftLegWalk)
        lineTo(146f, 350f + leftLegWalk)
        lineTo(144f, 280f + leftLegWalk * 0.5f)
        lineTo(158f, 228f + bY * 0.4f)
        close()
    }
    draw3dCylinder(leftLeg, Chief3dPalette.SuitMid, Chief3dPalette.SuitLight, Chief3dPalette.SuitShadow, isLeftLimb = true)

    val rightLeg = Path().apply {
        moveTo(164f, 228f + bY * 0.4f)
        lineTo(176f, 280f + rightLegWalk * 0.5f)
        lineTo(174f, 350f + rightLegWalk)
        lineTo(208f, 350f + rightLegWalk)
        lineTo(210f, 276f + rightLegWalk * 0.5f)
        lineTo(198f, 224f + bY * 0.4f)
        close()
    }
    draw3dCylinder(rightLeg, Chief3dPalette.SuitMid, Chief3dPalette.SuitLight, Chief3dPalette.SuitShadow, isLeftLimb = false)

    // 3D Retro High-Top Sneakers
    draw3dHighTopSneaker(
        x = 90f,
        y = 350f + leftLegWalk,
        width = 56f,
        height = 36f,
        primaryLight = Chief3dPalette.SuitLight,
        primaryMid = Chief3dPalette.SuitMid,
        primaryDark = Chief3dPalette.SuitShadow,
        accentColor = Color(0xFFF8FAFC),
        facingRight = false
    )
    draw3dHighTopSneaker(
        x = 172f,
        y = 350f + rightLegWalk,
        width = 56f,
        height = 36f,
        primaryLight = Chief3dPalette.SuitLight,
        primaryMid = Chief3dPalette.SuitMid,
        primaryDark = Chief3dPalette.SuitShadow,
        accentColor = Color(0xFFF8FAFC),
        facingRight = true
    )

    // 2. Volumetric 3D Torso (Flight Jumpsuit)
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
            colors = listOf(Chief3dPalette.SuitLight, Chief3dPalette.SuitMid, Chief3dPalette.SuitShadow),
            center = Offset(145f, torsoY + 45f),
            radius = 110f
        ),
        style = Fill
    )

    // 3D Brass Metallic Center Zipper
    draw3dZipper(160f, torsoY + 16f, torsoY + 128f, pullTabY = torsoY + 50f, metallicColor = Hd3dPalettes.GoldLight)

    // 3D White Star Insignia on Left Chest
    val starPos = Offset(136f, torsoY + 52f)
    draw3dSphere(starPos, radius = 7f, baseColor = Color(0xFFFFFFFF), highlightColor = Color(0xFFFFFFFF), shadowColor = Color(0xFF94A3B8))

    // 3D Gold Commander Epaulets on Shoulders
    drawRoundRect(
        brush = Brush.horizontalGradient(
            colors = listOf(Hd3dPalettes.GoldLight, Hd3dPalettes.GoldMid, Hd3dPalettes.GoldDark),
            startX = 96f,
            endX = 124f
        ),
        topLeft = Offset(96f, torsoY + 14f),
        size = Size(28f, 9f),
        cornerRadius = CornerRadius(3f, 3f)
    )
    drawRoundRect(
        brush = Brush.horizontalGradient(
            colors = listOf(Hd3dPalettes.GoldLight, Hd3dPalettes.GoldMid, Hd3dPalettes.GoldDark),
            startX = 196f,
            endX = 224f
        ),
        topLeft = Offset(196f, torsoY + 14f),
        size = Size(28f, 9f),
        cornerRadius = CornerRadius(3f, 3f)
    )

    // 3D Cross-Torso Flight Harness Straps
    drawLine(
        brush = Brush.linearGradient(
            colors = listOf(Color(0xFF334155), Color(0xFF0F172A)),
            start = Offset(104f, torsoY + 20f),
            end = Offset(204f, torsoY + 124f)
        ),
        start = Offset(104f, torsoY + 20f),
        end = Offset(204f, torsoY + 124f),
        strokeWidth = 8f
    )
    draw3dSphere(Offset(154f, torsoY + 72f), radius = 5f, baseColor = Hd3dPalettes.SilverMid, highlightColor = Hd3dPalettes.SilverLight, shadowColor = Hd3dPalettes.SilverDark)

    // 3D Arms
    // Left Arm (Clenched white talon fist)
    val leftArm = Path().apply {
        moveTo(102f, torsoY + 16f)
        lineTo(76f, torsoY + 70f)
        lineTo(96f, torsoY + 112f)
        lineTo(116f, torsoY + 106f)
        lineTo(102f, torsoY + 70f)
        lineTo(116f, torsoY + 24f)
        close()
    }
    draw3dCylinder(leftArm, Chief3dPalette.SuitMid, Chief3dPalette.SuitLight, Chief3dPalette.SuitShadow, isLeftLimb = true)
    draw3dSphere(Offset(102f, torsoY + 116f), radius = 10f, baseColor = Chief3dPalette.FeatherMid, highlightColor = Chief3dPalette.FeatherLight, shadowColor = Chief3dPalette.FeatherShadow)

    // Right Arm (Holding command smartphone)
    val rightArm = Path().apply {
        moveTo(218f, torsoY + 16f)
        lineTo(240f, torsoY + 70f)
        lineTo(222f, torsoY + 112f)
        lineTo(204f, torsoY + 106f)
        lineTo(218f, torsoY + 70f)
        lineTo(204f, torsoY + 24f)
        close()
    }
    draw3dCylinder(rightArm, Chief3dPalette.SuitMid, Chief3dPalette.SuitLight, Chief3dPalette.SuitShadow, isLeftLimb = false)
    draw3dSphere(Offset(216f, torsoY + 116f), radius = 10f, baseColor = Chief3dPalette.FeatherMid, highlightColor = Chief3dPalette.FeatherLight, shadowColor = Chief3dPalette.FeatherShadow)

    // 3D Tactical Command Smartphone with Glowing Green Screen
    drawRoundRect(
        brush = Brush.verticalGradient(
            colors = listOf(Color(0xFF334155), Color(0xFF0F172A)),
            startY = torsoY + 98f,
            endY = torsoY + 130f
        ),
        topLeft = Offset(212f, torsoY + 98f),
        size = Size(18f, 32f),
        cornerRadius = CornerRadius(4f, 4f)
    )
    drawRoundRect(
        brush = Brush.verticalGradient(
            colors = listOf(Chief3dPalette.PhoneGlow, Chief3dPalette.PhoneGreen),
            startY = torsoY + 102f,
            endY = torsoY + 126f
        ),
        topLeft = Offset(214f, torsoY + 102f),
        size = Size(14f, 24f),
        cornerRadius = CornerRadius(2f, 2f)
    )

    // 3. Volumetric 3D Bald Eagle Head & Golden Raptor Beak
    val headY = 18f + bY

    // Smooth Layered White Plumage Head (Spherical/Cranial Volume)
    val head = Path().apply {
        moveTo(120f, headY + 28f)
        cubicTo(124f, headY + 4f, 196f, headY + 4f, 200f, headY + 28f)
        cubicTo(214f, headY + 48f, 218f, headY + 72f, 198f, headY + 86f)
        cubicTo(180f, headY + 92f, 140f, headY + 92f, 122f, headY + 86f)
        cubicTo(102f, headY + 72f, 106f, headY + 48f, 120f, headY + 28f)
        close()
    }
    drawPath(
        head,
        brush = Brush.radialGradient(
            colors = listOf(Chief3dPalette.FeatherLight, Chief3dPalette.FeatherMid, Chief3dPalette.FeatherShadow),
            center = Offset(150f, headY + 36f),
            radius = 65f
        ),
        style = Fill
    )

    // Deep Orbital Brow Ridge
    val brow = Path().apply {
        moveTo(124f, headY + 40f)
        cubicTo(140f, headY + 38f, 180f, headY + 38f, 196f, headY + 40f)
        lineTo(194f, headY + 46f)
        cubicTo(178f, headY + 44f, 142f, headY + 44f, 126f, headY + 46f)
        close()
    }
    drawPath(brow, color = Chief3dPalette.FeatherShadow, style = Fill)

    // Powerful Curved 3D Golden Hooked Raptor Beak
    val beak = Path().apply {
        moveTo(146f, headY + 50f)
        lineTo(174f, headY + 50f)
        cubicTo(178f, headY + 62f, 174f, headY + 74f, 168f, headY + 86f)
        cubicTo(162f, headY + 92f, 158f, headY + 92f, 152f, headY + 86f)
        cubicTo(146f, headY + 74f, 142f, headY + 62f, 146f, headY + 50f)
        close()
    }
    drawPath(
        beak,
        brush = Brush.radialGradient(
            colors = listOf(Chief3dPalette.BeakLight, Chief3dPalette.BeakGold, Chief3dPalette.BeakShadow),
            center = Offset(156f, headY + 62f),
            radius = 35f
        ),
        style = Fill
    )
    // Nostril notch
    drawCircle(Chief3dPalette.BeakShadow, radius = 2f, center = Offset(153f, headY + 58f))
    drawCircle(Chief3dPalette.BeakShadow, radius = 2f, center = Offset(167f, headY + 58f))

    // Piercing 3D Predatory Amber Eyes
    if (!isBlinking) {
        draw3dEye(
            center = Offset(136f, headY + 46f),
            radius = 6.5f,
            irisColor = Chief3dPalette.EyeGold,
            irisGlowColor = Chief3dPalette.EyeGlow,
            eyelinerColor = Color(0xFF0F172A)
        )
        draw3dEye(
            center = Offset(184f, headY + 46f),
            radius = 6.5f,
            irisColor = Chief3dPalette.EyeGold,
            irisGlowColor = Chief3dPalette.EyeGlow,
            eyelinerColor = Color(0xFF0F172A)
        )
    }
}

fun DrawScope.drawChiefAvatarPortrait(isBlinking: Boolean) {
    // 320x320 64-Bit HD 3D Portrait View (Head & Shoulders, Star, Epaulets, Raptor Beak)
    // White Head Volume
    val head = Path().apply {
        moveTo(90f, 60f)
        cubicTo(110f, 15f, 210f, 15f, 230f, 60f)
        cubicTo(260f, 110f, 260f, 180f, 230f, 220f)
        cubicTo(190f, 240f, 130f, 240f, 90f, 220f)
        cubicTo(60f, 180f, 60f, 110f, 90f, 60f)
        close()
    }
    drawPath(
        head,
        brush = Brush.radialGradient(
            colors = listOf(Chief3dPalette.FeatherLight, Chief3dPalette.FeatherMid, Chief3dPalette.FeatherShadow),
            center = Offset(145f, 90f),
            radius = 130f
        ),
        style = Fill
    )

    // Brow Ridge
    val brow = Path().apply {
        moveTo(100f, 115f)
        cubicTo(130f, 110f, 190f, 110f, 220f, 115f)
        lineTo(215f, 128f)
        cubicTo(190f, 122f, 130f, 122f, 105f, 128f)
        close()
    }
    drawPath(brow, color = Chief3dPalette.FeatherShadow, style = Fill)

    // Large 3D Raptor Beak
    val beak = Path().apply {
        moveTo(132f, 130f)
        lineTo(188f, 130f)
        cubicTo(196f, 160f, 192f, 195f, 174f, 226f)
        cubicTo(166f, 236f, 154f, 236f, 146f, 226f)
        cubicTo(128f, 195f, 124f, 160f, 132f, 130f)
        close()
    }
    drawPath(
        beak,
        brush = Brush.radialGradient(
            colors = listOf(Chief3dPalette.BeakLight, Chief3dPalette.BeakGold, Chief3dPalette.BeakShadow),
            center = Offset(150f, 160f),
            radius = 70f
        ),
        style = Fill
    )
    draw3dSphere(Offset(144f, 148f), radius = 4f, baseColor = Chief3dPalette.BeakShadow, highlightColor = Chief3dPalette.BeakGold, shadowColor = Color(0xFF451A03), hasSpecular = false)
    draw3dSphere(Offset(176f, 148f), radius = 4f, baseColor = Chief3dPalette.BeakShadow, highlightColor = Chief3dPalette.BeakGold, shadowColor = Color(0xFF451A03), hasSpecular = false)

    // Eyes
    if (!isBlinking) {
        draw3dEye(
            center = Offset(118f, 132f),
            radius = 14f,
            irisColor = Chief3dPalette.EyeGold,
            irisGlowColor = Chief3dPalette.EyeGlow
        )
        draw3dEye(
            center = Offset(202f, 132f),
            radius = 14f,
            irisColor = Chief3dPalette.EyeGold,
            irisGlowColor = Chief3dPalette.EyeGlow
        )
    }

    // Flight Suit Torso & Shoulders
    val suit = Path().apply {
        moveTo(40f, 320f)
        lineTo(280f, 320f)
        lineTo(250f, 230f)
        lineTo(70f, 230f)
        close()
    }
    drawPath(
        suit,
        brush = Brush.radialGradient(
            colors = listOf(Chief3dPalette.SuitLight, Chief3dPalette.SuitMid, Chief3dPalette.SuitShadow),
            center = Offset(160f, 260f),
            radius = 120f
        ),
        style = Fill
    )
    draw3dZipper(160f, 230f, 320f, pullTabY = 250f, metallicColor = Hd3dPalettes.GoldLight)

    // 3D Epaulets
    drawRoundRect(
        brush = Brush.horizontalGradient(
            colors = listOf(Hd3dPalettes.GoldLight, Hd3dPalettes.GoldMid, Hd3dPalettes.GoldDark),
            startX = 50f,
            endX = 100f
        ),
        topLeft = Offset(50f, 230f),
        size = Size(50f, 16f),
        cornerRadius = CornerRadius(4f, 4f)
    )
    drawRoundRect(
        brush = Brush.horizontalGradient(
            colors = listOf(Hd3dPalettes.GoldLight, Hd3dPalettes.GoldMid, Hd3dPalettes.GoldDark),
            startX = 220f,
            endX = 270f
        ),
        topLeft = Offset(220f, 230f),
        size = Size(50f, 16f),
        cornerRadius = CornerRadius(4f, 4f)
    )
}
