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
 * Snoop — Chill Bloodhound Marksman.
 * 64-Bit HD 3D Character Model (Star Fox 64 Style in High Definition).
 *
 * Sculpted 3D Volumetric Features:
 * - Droopy bloodhound muzzle with heavy jowls, whisker dots, and relaxed half-lidded eyes.
 * - Slouchy charcoal knit beanie with yellow lightbulb emblem patch.
 * - Long cascading dreadlocks with cylindrical 3D gold beads reflecting light.
 * - Open olive drab MA-1 flight bomber jacket with billowing volume and safety-orange quilted interior.
 * - Shark tooth pendant on cord over white scoop undershirt.
 * - Baggy stacked indigo jeans and retro high-top sneakers.
 * - Soft underfoot ambient occlusion contact shadow.
 */
object Snoop3dPalette {
    val FurLight = Color(0xFFD97706)
    val FurTan = Color(0xFFB45309)
    val FurShadow = Color(0xFF78350F)
    val BeanieCharcoal = Color(0xFF1E293B)
    val BeanieDark = Color(0xFF0F172A)

    val DreadlockDark = Color(0xFF1E293B)
    val GoldBead = Color(0xFFFACC15)
    val GoldLight = Color(0xFFFEF08A)

    val BomberOliveLight = Color(0xFF65A30D)
    val BomberOlive = Color(0xFF4D7C0F)
    val BomberOliveDark = Color(0xFF365314)
    val LiningOrange = Color(0xFFEA580C)

    val DenimBlue = Color(0xFF1E293B)
}

fun DrawScope.drawSnoopFullBody(
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

    // 1. Baggy Stacked Denim Jeans
    val leftLeg = Path().apply {
        moveTo(120f, 224f + bY * 0.4f)
        lineTo(108f, 276f + leftLegWalk * 0.5f)
        lineTo(110f, 350f + leftLegWalk)
        lineTo(148f, 350f + leftLegWalk)
        lineTo(146f, 280f + leftLegWalk * 0.5f)
        lineTo(158f, 228f + bY * 0.4f)
        close()
    }
    draw3dCylinder(leftLeg, Snoop3dPalette.DenimBlue, Color(0xFF334155), Snoop3dPalette.BeanieDark, isLeftLimb = true)

    val rightLeg = Path().apply {
        moveTo(162f, 228f + bY * 0.4f)
        lineTo(174f, 280f + rightLegWalk * 0.5f)
        lineTo(172f, 350f + rightLegWalk)
        lineTo(210f, 350f + rightLegWalk)
        lineTo(212f, 276f + rightLegWalk * 0.5f)
        lineTo(200f, 224f + bY * 0.4f)
        close()
    }
    draw3dCylinder(rightLeg, Snoop3dPalette.DenimBlue, Color(0xFF334155), Snoop3dPalette.BeanieDark, isLeftLimb = false)

    // Retro High-Tops
    draw3dHighTopSneaker(
        x = 90f,
        y = 350f + leftLegWalk,
        width = 56f,
        height = 36f,
        primaryLight = Snoop3dPalette.BomberOliveLight,
        primaryMid = Snoop3dPalette.BomberOlive,
        primaryDark = Snoop3dPalette.BomberOliveDark,
        accentColor = Color(0xFFF8FAFC),
        facingRight = false
    )
    draw3dHighTopSneaker(
        x = 172f,
        y = 350f + rightLegWalk,
        width = 56f,
        height = 36f,
        primaryLight = Snoop3dPalette.BomberOliveLight,
        primaryMid = Snoop3dPalette.BomberOlive,
        primaryDark = Snoop3dPalette.BomberOliveDark,
        accentColor = Color(0xFFF8FAFC),
        facingRight = true
    )

    // 2. Open Olive MA-1 Bomber Jacket & Orange Lining
    val torsoY = 96f + bY

    // Billowing Olive Torso with Open Front
    val bomber = Path().apply {
        moveTo(100f, torsoY + 16f)
        cubicTo(120f, torsoY + 12f, 200f, torsoY + 12f, 220f, torsoY + 16f)
        lineTo(214f, torsoY + 130f)
        lineTo(106f, torsoY + 130f)
        close()
    }
    drawPath(
        bomber,
        brush = Brush.radialGradient(
            colors = listOf(Snoop3dPalette.BomberOliveLight, Snoop3dPalette.BomberOlive, Snoop3dPalette.BomberOliveDark),
            center = Offset(150f, torsoY + 45f),
            radius = 110f
        ),
        style = Fill
    )

    // Quilted Safety-Orange Lining Peeking Open
    val lining = Path().apply {
        moveTo(134f, torsoY + 18f)
        lineTo(186f, torsoY + 18f)
        lineTo(194f, torsoY + 126f)
        lineTo(126f, torsoY + 126f)
        close()
    }
    drawPath(lining, color = Snoop3dPalette.LiningOrange, style = Fill)

    // White Scoop Undershirt with Shark Tooth Pendant
    val undershirt = Path().apply {
        moveTo(144f, torsoY + 20f)
        lineTo(176f, torsoY + 20f)
        lineTo(182f, torsoY + 124f)
        lineTo(138f, torsoY + 124f)
        close()
    }
    drawPath(undershirt, color = Color(0xFFF8FAFC), style = Fill)
    // Shark Tooth
    val tooth = Path().apply {
        moveTo(156f, torsoY + 46f)
        lineTo(164f, torsoY + 46f)
        lineTo(160f, torsoY + 58f)
        close()
    }
    drawPath(tooth, color = Color(0xFFF1F5F9), style = Fill)

    // Arms with Hands Casually in Pockets
    val leftArm = Path().apply {
        moveTo(100f, torsoY + 16f)
        cubicTo(76f, torsoY + 60f, 90f, torsoY + 95f, 122f, torsoY + 106f)
        lineTo(112f, torsoY + 95f)
        lineTo(102f, torsoY + 65f)
        lineTo(114f, torsoY + 24f)
        close()
    }
    draw3dCylinder(leftArm, Snoop3dPalette.BomberOlive, Snoop3dPalette.BomberOliveLight, Snoop3dPalette.BomberOliveDark, isLeftLimb = true)

    val rightArm = Path().apply {
        moveTo(220f, torsoY + 16f)
        cubicTo(244f, torsoY + 60f, 230f, torsoY + 95f, 198f, torsoY + 106f)
        lineTo(208f, torsoY + 95f)
        lineTo(218f, torsoY + 65f)
        lineTo(206f, torsoY + 24f)
        close()
    }
    draw3dCylinder(rightArm, Snoop3dPalette.BomberOlive, Snoop3dPalette.BomberOliveLight, Snoop3dPalette.BomberOliveDark, isLeftLimb = false)

    // 3. Bloodhound Head, Beanie & Cascading Dreadlocks
    val headY = 18f + bY

    // Droopy Jowls & Long Skull
    val head = Path().apply {
        moveTo(116f, headY + 28f)
        cubicTo(124f, headY + 8f, 196f, headY + 8f, 204f, headY + 28f)
        cubicTo(218f, headY + 50f, 222f, headY + 76f, 206f, headY + 92f)
        lineTo(160f, headY + 94f)
        lineTo(114f, headY + 92f)
        cubicTo(98f, headY + 76f, 102f, headY + 50f, 116f, headY + 28f)
        close()
    }
    drawPath(
        head,
        brush = Brush.radialGradient(
            colors = listOf(Snoop3dPalette.FurLight, Snoop3dPalette.FurTan, Snoop3dPalette.FurShadow),
            center = Offset(150f, headY + 45f),
            radius = 65f
        ),
        style = Fill
    )

    // Slouchy Charcoal Knit Beanie
    val beanie = Path().apply {
        moveTo(112f, headY + 28f)
        cubicTo(120f, headY - 4f, 200f, headY - 4f, 208f, headY + 28f)
        lineTo(198f, headY + 36f)
        cubicTo(180f, headY + 32f, 140f, headY + 32f, 122f, headY + 36f)
        close()
    }
    drawPath(
        beanie,
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFF334155), Snoop3dPalette.BeanieCharcoal, Snoop3dPalette.BeanieDark),
            center = Offset(150f, headY + 12f),
            radius = 55f
        ),
        style = Fill
    )
    // Yellow Lightbulb Patch on Beanie
    draw3dSphere(Offset(160f, headY + 16f), radius = 5.5f, baseColor = Color(0xFFFACC15), highlightColor = Color(0xFFFEF08A), shadowColor = Color(0xFFCA8A04))

    // Cascading Dreadlocks with Cylindrical Gold Beads
    // Left Dreadlocks
    for (d in 0..1) {
        val dx = 104f + d * 10f
        drawLine(
            color = Snoop3dPalette.DreadlockDark,
            start = Offset(dx, headY + 32f),
            end = Offset(dx - 4f, headY + 95f),
            strokeWidth = 5.5f
        )
        // Gold Bead
        draw3dSphere(Offset(dx - 2f, headY + 65f), radius = 4f, baseColor = Snoop3dPalette.GoldBead, highlightColor = Snoop3dPalette.GoldLight, shadowColor = Color(0xFF854D0E))
    }
    // Right Dreadlocks
    for (d in 0..1) {
        val dx = 206f - d * 10f
        drawLine(
            color = Snoop3dPalette.DreadlockDark,
            start = Offset(dx, headY + 32f),
            end = Offset(dx + 4f, headY + 95f),
            strokeWidth = 5.5f
        )
        // Gold Bead
        draw3dSphere(Offset(dx + 2f, headY + 65f), radius = 4f, baseColor = Snoop3dPalette.GoldBead, highlightColor = Snoop3dPalette.GoldLight, shadowColor = Color(0xFF854D0E))
    }

    // Heavy Droopy Muzzle & Bloodhound Jowls
    val jowls = Path().apply {
        moveTo(134f, headY + 52f)
        lineTo(186f, headY + 52f)
        cubicTo(192f, headY + 76f, 180f, headY + 92f, 160f, headY + 94f)
        cubicTo(140f, headY + 92f, 128f, headY + 76f, 134f, headY + 52f)
        close()
    }
    drawPath(
        jowls,
        brush = Brush.radialGradient(
            colors = listOf(Snoop3dPalette.FurLight, Snoop3dPalette.FurTan, Snoop3dPalette.FurShadow),
            center = Offset(160f, headY + 70f),
            radius = 35f
        ),
        style = Fill
    )
    draw3dSphere(Offset(160f, headY + 65f), radius = 6.5f, baseColor = Color(0xFF1E293B), highlightColor = Color(0xFF475569), shadowColor = Color(0xFF05080E))

    // Relaxed Half-Lidded Heavy Bloodhound Eyes
    if (!isBlinking) {
        draw3dEye(center = Offset(136f, headY + 46f), radius = 6f, irisColor = Color(0xFF92400E), irisGlowColor = Color(0xFFD97706))
        draw3dEye(center = Offset(184f, headY + 46f), radius = 6f, irisColor = Color(0xFF92400E), irisGlowColor = Color(0xFFD97706))
        // Droopy heavy eyelids
        drawRoundRect(color = Snoop3dPalette.FurShadow, topLeft = Offset(129f, headY + 40f), size = Size(14f, 4f), cornerRadius = CornerRadius(2f, 2f))
        drawRoundRect(color = Snoop3dPalette.FurShadow, topLeft = Offset(177f, headY + 40f), size = Size(14f, 4f), cornerRadius = CornerRadius(2f, 2f))
    }
}

fun DrawScope.drawSnoopAvatarPortrait(isBlinking: Boolean) {
    // 320x320 64-Bit HD 3D Portrait View (Slouchy Beanie, Dreadlocks, Gold Beads, Droopy Muzzle)
    val head = Path().apply { moveTo(90f, 90f); cubicTo(110f, 30f, 210f, 30f, 230f, 90f); lineTo(250f, 160f); lineTo(225f, 230f); lineTo(160f, 235f); lineTo(95f, 230f); lineTo(70f, 160f); close() }
    drawPath(head, brush = Brush.radialGradient(colors = listOf(Snoop3dPalette.FurLight, Snoop3dPalette.FurTan, Snoop3dPalette.FurShadow), center = Offset(150f, 125f), radius = 125f), style = Fill)

    // Beanie
    val beanie = Path().apply { moveTo(80f, 95f); cubicTo(100f, 10f, 220f, 10f, 240f, 95f); close() }
    drawPath(beanie, brush = Brush.radialGradient(colors = listOf(Color(0xFF334155), Snoop3dPalette.BeanieCharcoal, Snoop3dPalette.BeanieDark), center = Offset(160f, 45f), radius = 90f), style = Fill)
    draw3dSphere(Offset(160f, 55f), radius = 12f, baseColor = Color(0xFFFACC15), highlightColor = Color(0xFFFEF08A), shadowColor = Color(0xFFCA8A04))

    // Dreadlocks with Gold Beads
    for (d in 0..2) {
        val lx = 55f + d * 18f
        drawLine(color = Snoop3dPalette.DreadlockDark, start = Offset(lx, 100f), end = Offset(lx - 10f, 250f), strokeWidth = 10f)
        draw3dSphere(Offset(lx - 5f, 175f), radius = 8f, baseColor = Snoop3dPalette.GoldBead, highlightColor = Snoop3dPalette.GoldLight, shadowColor = Color(0xFF854D0E))
        val rx = 265f - d * 18f
        drawLine(color = Snoop3dPalette.DreadlockDark, start = Offset(rx, 100f), end = Offset(rx + 10f, 250f), strokeWidth = 10f)
        draw3dSphere(Offset(rx + 5f, 175f), radius = 8f, baseColor = Snoop3dPalette.GoldBead, highlightColor = Snoop3dPalette.GoldLight, shadowColor = Color(0xFF854D0E))
    }

    // Heavy Muzzle
    draw3dSphere(Offset(160f, 180f), radius = 35f, baseColor = Snoop3dPalette.FurTan, highlightColor = Snoop3dPalette.FurLight, shadowColor = Snoop3dPalette.FurShadow)
    draw3dSphere(Offset(160f, 165f), radius = 14f, baseColor = Color(0xFF1E293B), highlightColor = Color(0xFF475569), shadowColor = Color(0xFF05080E))

    // Eyes
    if (!isBlinking) {
        draw3dEye(center = Offset(118f, 135f), radius = 13f, irisColor = Color(0xFF92400E), irisGlowColor = Color(0xFFD97706))
        draw3dEye(center = Offset(202f, 135f), radius = 13f, irisColor = Color(0xFF92400E), irisGlowColor = Color(0xFFD97706))
        drawRoundRect(color = Snoop3dPalette.FurShadow, topLeft = Offset(105f, 122f), size = Size(26f, 8f), cornerRadius = CornerRadius(4f, 4f))
        drawRoundRect(color = Snoop3dPalette.FurShadow, topLeft = Offset(189f, 122f), size = Size(26f, 8f), cornerRadius = CornerRadius(4f, 4f))
    }

    // Bomber & Orange Lining
    val bomber = Path().apply { moveTo(40f, 320f); lineTo(280f, 320f); lineTo(250f, 250f); lineTo(70f, 250f); close() }
    drawPath(bomber, brush = Brush.radialGradient(colors = listOf(Snoop3dPalette.BomberOliveLight, Snoop3dPalette.BomberOlive, Snoop3dPalette.BomberOliveDark), center = Offset(160f, 280f), radius = 120f), style = Fill)
}
