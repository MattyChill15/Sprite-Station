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
 * Wiz — Agile Raccoon Systems Architect.
 * 64-Bit HD 3D Character Model (Star Fox 64 Style in High Definition).
 *
 * Height-Locked: Stature is strictly ~0.75f relative to Cudi (compact build).
 *
 * Sculpted 3D Volumetric Features:
 * - Iconic bandit eye mask, white eye rims, white muzzle whiskers, and chin goatee.
 * - Ribbed navy dockworker beanie with gold wire aviators pushed up on the brim.
 * - Bushy 3D raccoon tail with 5 alternating charcoal and light grey rings.
 * - Distressed brown leather cross-body messenger satchel with brass clasp, spiral notebook edge,
 *   and glowing cyan stylus in side slot.
 * - Cuffed raw denim jeans with turn-up selvedge and brown skate sneakers.
 * - Soft underfoot ambient occlusion contact shadow.
 */
object Wiz3dPalette {
    val FurGrey = Color(0xFF64748B)
    val FurDark = Color(0xFF334155)
    val FurShadow = Color(0xFF1E293B)
    val MaskBlack = Color(0xFF0F172A)
    val FurWhite = Color(0xFFF8FAFC)

    val BeanieNavy = Color(0xFF1E3A8A)
    val BeanieNavyLight = Color(0xFF2563EB)
    val AviatorGold = Color(0xFFF59E0B)
    val AviatorLens = Color(0xFF0F172A)

    val SatchelBrown = Color(0xFF78350F)
    val SatchelLeather = Color(0xFF92400E)
    val StylusCyan = Color(0xFF00E5FF)

    val DenimBlue = Color(0xFF1E293B)
    val SelvedgeWhite = Color(0xFFF1F5F9)
}

fun DrawScope.drawWizFullBody(
    breatheY: Float,
    walkOffset: Float,
    isWalking: Boolean,
    isBlinking: Boolean
) {
    val bY = breatheY
    val leftLegWalk = walkOffset
    val rightLegWalk = -walkOffset

    // 0. 3D Ground Contact Shadow (Compact footprint)
    draw3dContactShadow(160f, 382f, radiusX = 55f, radiusY = 12f)

    // 1. Bushy Ringed 3D Raccoon Tail (Curving upward to viewer's right)
    val tail = Path().apply {
        moveTo(180f, 260f + bY)
        cubicTo(240f, 230f, 280f, 280f, 260f, 360f)
        cubicTo(240f, 385f, 210f, 340f, 175f, 280f + bY)
        close()
    }
    drawPath(
        tail,
        brush = Brush.radialGradient(
            colors = listOf(Wiz3dPalette.FurGrey, Wiz3dPalette.FurDark, Wiz3dPalette.FurShadow),
            center = Offset(220f, 290f),
            radius = 80f
        ),
        style = Fill
    )
    // 5 Alternating Volumetric Rings
    for (r in 0..4) {
        val ry = 270f + r * 18f
        val rx = 190f + r * 12f
        val ringColor = if (r % 2 == 0) Wiz3dPalette.MaskBlack else Wiz3dPalette.FurWhite
        drawRoundRect(
            color = ringColor.copy(alpha = 0.85f),
            topLeft = Offset(rx, ry),
            size = Size(24f, 12f),
            cornerRadius = CornerRadius(4f, 4f)
        )
    }

    // 2. Cuffed Denim Jeans Legs
    val leftLeg = Path().apply {
        moveTo(126f, 240f + bY * 0.4f)
        lineTo(116f, 286f + leftLegWalk * 0.5f)
        lineTo(118f, 350f + leftLegWalk)
        lineTo(148f, 350f + leftLegWalk)
        lineTo(146f, 290f + leftLegWalk * 0.5f)
        lineTo(156f, 244f + bY * 0.4f)
        close()
    }
    draw3dCylinder(leftLeg, Wiz3dPalette.DenimBlue, Color(0xFF334155), Wiz3dPalette.MaskBlack, isLeftLimb = true)
    // White Selvedge Cuff Turn-Up
    drawRoundRect(
        color = Wiz3dPalette.SelvedgeWhite,
        topLeft = Offset(118f, 342f + leftLegWalk),
        size = Size(30f, 8f),
        cornerRadius = CornerRadius(2f, 2f)
    )

    val rightLeg = Path().apply {
        moveTo(164f, 244f + bY * 0.4f)
        lineTo(174f, 290f + rightLegWalk * 0.5f)
        lineTo(172f, 350f + rightLegWalk)
        lineTo(202f, 350f + rightLegWalk)
        lineTo(204f, 286f + rightLegWalk * 0.5f)
        lineTo(194f, 240f + bY * 0.4f)
        close()
    }
    draw3dCylinder(rightLeg, Wiz3dPalette.DenimBlue, Color(0xFF334155), Wiz3dPalette.MaskBlack, isLeftLimb = false)
    // White Selvedge Cuff Turn-Up
    drawRoundRect(
        color = Wiz3dPalette.SelvedgeWhite,
        topLeft = Offset(172f, 342f + rightLegWalk),
        size = Size(30f, 8f),
        cornerRadius = CornerRadius(2f, 2f)
    )

    // Brown Skate Sneakers
    draw3dHighTopSneaker(
        x = 94f,
        y = 350f + leftLegWalk,
        width = 52f,
        height = 34f,
        primaryLight = Wiz3dPalette.SatchelLeather,
        primaryMid = Wiz3dPalette.SatchelBrown,
        primaryDark = Color(0xFF451A03),
        accentColor = Color(0xFFF8FAFC),
        facingRight = false
    )
    draw3dHighTopSneaker(
        x = 174f,
        y = 350f + rightLegWalk,
        width = 52f,
        height = 34f,
        primaryLight = Wiz3dPalette.SatchelLeather,
        primaryMid = Wiz3dPalette.SatchelBrown,
        primaryDark = Color(0xFF451A03),
        accentColor = Color(0xFFF8FAFC),
        facingRight = true
    )

    // 3. Compact Torso & Leather Satchel
    val torsoY = 110f + bY

    val sweater = Path().apply {
        moveTo(108f, torsoY + 16f)
        cubicTo(122f, torsoY + 12f, 198f, torsoY + 12f, 212f, torsoY + 16f)
        lineTo(202f, torsoY + 120f)
        lineTo(118f, torsoY + 120f)
        close()
    }
    drawPath(
        sweater,
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFF475569), Wiz3dPalette.FurDark, Wiz3dPalette.FurShadow),
            center = Offset(150f, torsoY + 40f),
            radius = 100f
        ),
        style = Fill
    )

    // Distressed Brown Leather Cross-Body Satchel Strap
    drawLine(
        brush = Brush.linearGradient(
            colors = listOf(Wiz3dPalette.SatchelLeather, Wiz3dPalette.SatchelBrown),
            start = Offset(112f, torsoY + 20f),
            end = Offset(204f, torsoY + 110f)
        ),
        start = Offset(112f, torsoY + 20f),
        end = Offset(204f, torsoY + 110f),
        strokeWidth = 9f
    )

    // Satchel Pouch at Hip
    drawRoundRect(
        brush = Brush.verticalGradient(
            colors = listOf(Wiz3dPalette.SatchelLeather, Wiz3dPalette.SatchelBrown, Color(0xFF451A03)),
            startY = torsoY + 90f,
            endY = torsoY + 130f
        ),
        topLeft = Offset(192f, torsoY + 90f),
        size = Size(26f, 36f),
        cornerRadius = CornerRadius(4f, 4f)
    )
    // Brass Clasp
    draw3dSphere(Offset(205f, torsoY + 106f), radius = 3.5f, baseColor = Hd3dPalettes.GoldMid, highlightColor = Hd3dPalettes.GoldLight, shadowColor = Hd3dPalettes.GoldDark)
    // Glowing Cyan Stylus
    drawLine(
        color = Wiz3dPalette.StylusCyan,
        start = Offset(214f, torsoY + 86f),
        end = Offset(214f, torsoY + 104f),
        strokeWidth = 2.5f
    )

    // Arms
    val leftArm = Path().apply {
        moveTo(108f, torsoY + 16f)
        lineTo(86f, torsoY + 65f)
        lineTo(104f, torsoY + 104f)
        lineTo(120f, torsoY + 98f)
        lineTo(108f, torsoY + 65f)
        lineTo(118f, torsoY + 24f)
        close()
    }
    draw3dCylinder(leftArm, Wiz3dPalette.FurDark, Color(0xFF475569), Wiz3dPalette.FurShadow, isLeftLimb = true)
    draw3dSphere(Offset(108f, torsoY + 108f), radius = 8f, baseColor = Wiz3dPalette.FurDark, highlightColor = Color(0xFF475569), shadowColor = Wiz3dPalette.FurShadow)

    val rightArm = Path().apply {
        moveTo(212f, torsoY + 16f)
        lineTo(232f, torsoY + 65f)
        lineTo(218f, torsoY + 104f)
        lineTo(202f, torsoY + 98f)
        lineTo(212f, torsoY + 65f)
        lineTo(202f, torsoY + 24f)
        close()
    }
    draw3dCylinder(rightArm, Wiz3dPalette.FurDark, Color(0xFF475569), Wiz3dPalette.FurShadow, isLeftLimb = false)
    draw3dSphere(Offset(214f, torsoY + 108f), radius = 8f, baseColor = Wiz3dPalette.FurDark, highlightColor = Color(0xFF475569), shadowColor = Wiz3dPalette.FurShadow)

    // 4. Raccoon Head, Dockworker Beanie & Aviators
    val headY = 32f + bY

    // Rounded Ears with White Fur Rim
    draw3dSphere(Offset(116f, headY + 16f), radius = 10f, baseColor = Wiz3dPalette.FurDark, highlightColor = Wiz3dPalette.FurGrey, shadowColor = Wiz3dPalette.FurShadow)
    draw3dSphere(Offset(116f, headY + 16f), radius = 6f, baseColor = Wiz3dPalette.FurWhite, highlightColor = Color(0xFFFFFFFF), shadowColor = Color(0xFFCBD5E1), hasSpecular = false)

    draw3dSphere(Offset(204f, headY + 16f), radius = 10f, baseColor = Wiz3dPalette.FurDark, highlightColor = Wiz3dPalette.FurGrey, shadowColor = Wiz3dPalette.FurShadow)
    draw3dSphere(Offset(204f, headY + 16f), radius = 6f, baseColor = Wiz3dPalette.FurWhite, highlightColor = Color(0xFFFFFFFF), shadowColor = Color(0xFFCBD5E1), hasSpecular = false)

    // Head Volume
    val head = Path().apply {
        moveTo(116f, headY + 26f)
        cubicTo(124f, headY + 8f, 196f, headY + 8f, 204f, headY + 26f)
        cubicTo(218f, headY + 48f, 216f, headY + 72f, 200f, headY + 84f)
        lineTo(160f, headY + 86f)
        lineTo(120f, headY + 84f)
        cubicTo(104f, headY + 72f, 102f, headY + 48f, 116f, headY + 26f)
        close()
    }
    drawPath(
        head,
        brush = Brush.radialGradient(
            colors = listOf(Wiz3dPalette.FurGrey, Wiz3dPalette.FurDark, Wiz3dPalette.FurShadow),
            center = Offset(150f, headY + 40f),
            radius = 60f
        ),
        style = Fill
    )

    // Navy Dockworker Beanie on Crown
    val beanie = Path().apply {
        moveTo(114f, headY + 26f)
        cubicTo(122f, headY - 2f, 198f, headY - 2f, 206f, headY + 26f)
        lineTo(198f, headY + 32f)
        cubicTo(180f, headY + 28f, 140f, headY + 28f, 122f, headY + 32f)
        close()
    }
    drawPath(
        beanie,
        brush = Brush.radialGradient(
            colors = listOf(Wiz3dPalette.BeanieNavyLight, Wiz3dPalette.BeanieNavy, Color(0xFF0F172A)),
            center = Offset(150f, headY + 10f),
            radius = 50f
        ),
        style = Fill
    )

    // Gold Wire Aviators Pushed Up onto Beanie Brim
    drawRoundRect(
        brush = Brush.radialGradient(colors = listOf(Color(0xFF334155), Wiz3dPalette.AviatorLens), center = Offset(136f, headY + 24f), radius = 16f),
        topLeft = Offset(122f, headY + 16f),
        size = Size(30f, 15f),
        cornerRadius = CornerRadius(4f, 4f)
    )
    drawRoundRect(
        brush = Brush.radialGradient(colors = listOf(Color(0xFF334155), Wiz3dPalette.AviatorLens), center = Offset(184f, headY + 24f), radius = 16f),
        topLeft = Offset(168f, headY + 16f),
        size = Size(30f, 15f),
        cornerRadius = CornerRadius(4f, 4f)
    )
    drawLine(Wiz3dPalette.AviatorGold, Offset(152f, headY + 24f), Offset(168f, headY + 24f), strokeWidth = 2.2f)

    // Bandit Mask across eyes
    val mask = Path().apply {
        moveTo(118f, headY + 40f)
        lineTo(202f, headY + 40f)
        cubicTo(196f, headY + 60f, 178f, headY + 60f, 160f, headY + 54f)
        cubicTo(142f, headY + 60f, 124f, headY + 60f, 118f, headY + 40f)
        close()
    }
    drawPath(mask, color = Wiz3dPalette.MaskBlack, style = Fill)

    // White Muzzle & Goatee
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
            colors = listOf(Color(0xFFFFFFFF), Color(0xFFF1F5F9), Color(0xFFCBD5E1)),
            center = Offset(160f, headY + 68f),
            radius = 32f
        ),
        style = Fill
    )
    draw3dSphere(Offset(160f, headY + 62f), radius = 5f, baseColor = Color(0xFF1E293B), highlightColor = Color(0xFF475569), shadowColor = Color(0xFF05080E))
    // Black Goatee tuft
    drawCircle(Color(0xFF0F172A), radius = 3.5f, center = Offset(160f, headY + 82f))

    // Eyes
    if (!isBlinking) {
        draw3dEye(center = Offset(136f, headY + 46f), radius = 6f, irisColor = Color(0xFFD97706), irisGlowColor = Color(0xFFFBBF24))
        draw3dEye(center = Offset(184f, headY + 46f), radius = 6f, irisColor = Color(0xFFD97706), irisGlowColor = Color(0xFFFBBF24))
    }
}

fun DrawScope.drawWizAvatarPortrait(isBlinking: Boolean) {
    // 320x320 64-Bit HD 3D Portrait View (Aviators, Beanie, Bandit Mask, Satchel Strap)
    val head = Path().apply { moveTo(90f, 90f); cubicTo(110f, 30f, 210f, 30f, 230f, 90f); lineTo(250f, 160f); lineTo(225f, 225f); lineTo(160f, 235f); lineTo(95f, 225f); lineTo(70f, 160f); close() }
    drawPath(head, brush = Brush.radialGradient(colors = listOf(Wiz3dPalette.FurGrey, Wiz3dPalette.FurDark, Wiz3dPalette.FurShadow), center = Offset(150f, 125f), radius = 125f), style = Fill)

    // Beanie
    val beanie = Path().apply { moveTo(80f, 95f); cubicTo(100f, 15f, 220f, 15f, 240f, 95f); close() }
    drawPath(beanie, brush = Brush.radialGradient(colors = listOf(Wiz3dPalette.BeanieNavyLight, Wiz3dPalette.BeanieNavy, Color(0xFF0F172A)), center = Offset(160f, 50f), radius = 90f), style = Fill)

    // Aviators
    drawRoundRect(brush = Brush.radialGradient(colors = listOf(Color(0xFF334155), Wiz3dPalette.AviatorLens), center = Offset(115f, 85f), radius = 32f), topLeft = Offset(85f, 65f), size = Size(65f, 34f), cornerRadius = CornerRadius(8f, 8f))
    drawRoundRect(brush = Brush.radialGradient(colors = listOf(Color(0xFF334155), Wiz3dPalette.AviatorLens), center = Offset(205f, 85f), radius = 32f), topLeft = Offset(170f, 65f), size = Size(65f, 34f), cornerRadius = CornerRadius(8f, 8f))

    // Bandit Mask
    val mask = Path().apply { moveTo(80f, 120f); lineTo(240f, 120f); cubicTo(230f, 165f, 190f, 165f, 160f, 150f); cubicTo(130f, 165f, 90f, 165f, 80f, 120f); close() }
    drawPath(mask, color = Wiz3dPalette.MaskBlack, style = Fill)

    // Muzzle
    draw3dSphere(Offset(160f, 178f), radius = 32f, baseColor = Color(0xFFF1F5F9), highlightColor = Color(0xFFFFFFFF), shadowColor = Color(0xFFCBD5E1))
    draw3dSphere(Offset(160f, 165f), radius = 12f, baseColor = Color(0xFF1E293B), highlightColor = Color(0xFF475569), shadowColor = Color(0xFF05080E))

    // Eyes
    if (!isBlinking) {
        draw3dEye(center = Offset(118f, 135f), radius = 13f, irisColor = Color(0xFFD97706), irisGlowColor = Color(0xFFFBBF24))
        draw3dEye(center = Offset(202f, 135f), radius = 13f, irisColor = Color(0xFFD97706), irisGlowColor = Color(0xFFFBBF24))
    }

    // Leather Satchel Strap & Sweater
    val sweater = Path().apply { moveTo(40f, 320f); lineTo(280f, 320f); lineTo(250f, 250f); lineTo(70f, 250f); close() }
    drawPath(sweater, brush = Brush.radialGradient(colors = listOf(Color(0xFF475569), Wiz3dPalette.FurDark, Wiz3dPalette.FurShadow), center = Offset(160f, 280f), radius = 120f), style = Fill)
    drawLine(Wiz3dPalette.SatchelLeather, Offset(60f, 250f), Offset(250f, 320f), strokeWidth = 20f)
}
