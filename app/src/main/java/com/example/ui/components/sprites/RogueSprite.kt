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
 * Rogue — Brindle Staffy Lead Flight Mechanic.
 * 64-Bit HD 3D Character Model (Star Fox 64 Style in High Definition).
 *
 * Sculpted 3D Volumetric Features:
 * - Broad muscular 3D Staffordshire Bull Terrier skull with powerful jaw and white chest blaze.
 * - Warm golden-tan coat with smooth tiger-brindle stripes.
 * - Metallic 3D racing/welding goggles pushed up onto forehead with glossy tinted lenses.
 * - Heavy purple 3D spiked collar with sculpted silver pyramid studs.
 * - Charcoal mechanic vest with safety-yellow lapels and muscular 3D arms.
 * - Right hand firmly wielding heavy 3D steel pipe wrench with knurled adjustment ring.
 * - Heavy reinforced work trousers and 3D lugged steel-toe combat boots.
 * - Soft underfoot ambient occlusion contact shadow.
 */
object Rogue3dPalette {
    val FurLight = Color(0xFFD97706)
    val FurMid = Color(0xFFB45309)
    val FurShadow = Color(0xFF78350F)
    val FurBrindle = Color(0xFF451A03)
    val FurWhite = Color(0xFFF8FAFC)

    val CollarPurple = Color(0xFF7C3AED)
    val StudSilver = Color(0xFFE2E8F0)

    val VestDark = Color(0xFF1E293B)
    val VestShadow = Color(0xFF0F172A)
    val LapelYellow = Color(0xFFFACC15)

    val GoggleGold = Color(0xFFF59E0B)
    val GoggleLens = Color(0xFF0F172A)

    val WrenchRed = Color(0xFFDC2626)
    val WrenchSteel = Color(0xFF94A3B8)
}

fun DrawScope.drawRogueFullBody(
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

    // 1. Heavy Work Trousers (Broad, sturdy stance)
    val leftLeg = Path().apply {
        moveTo(122f, 224f + bY * 0.4f)
        lineTo(110f, 276f + leftLegWalk * 0.5f)
        lineTo(112f, 350f + leftLegWalk)
        lineTo(148f, 350f + leftLegWalk)
        lineTo(146f, 280f + leftLegWalk * 0.5f)
        lineTo(158f, 228f + bY * 0.4f)
        close()
    }
    draw3dCylinder(leftLeg, Rogue3dPalette.VestDark, Color(0xFF334155), Rogue3dPalette.VestShadow, isLeftLimb = true)

    val rightLeg = Path().apply {
        moveTo(162f, 228f + bY * 0.4f)
        lineTo(174f, 280f + rightLegWalk * 0.5f)
        lineTo(172f, 350f + rightLegWalk)
        lineTo(208f, 350f + rightLegWalk)
        lineTo(210f, 276f + rightLegWalk * 0.5f)
        lineTo(198f, 224f + bY * 0.4f)
        close()
    }
    draw3dCylinder(rightLeg, Rogue3dPalette.VestDark, Color(0xFF334155), Rogue3dPalette.VestShadow, isLeftLimb = false)

    // 3D Lugged Combat Boots
    draw3dCombatBoot(
        x = 90f,
        y = 350f + leftLegWalk,
        width = 58f,
        height = 36f,
        leatherLight = Color(0xFF334155),
        leatherMid = Color(0xFF1E293B),
        leatherDark = Color(0xFF0F172A),
        facingRight = false
    )
    draw3dCombatBoot(
        x = 172f,
        y = 350f + rightLegWalk,
        width = 58f,
        height = 36f,
        leatherLight = Color(0xFF334155),
        leatherMid = Color(0xFF1E293B),
        leatherDark = Color(0xFF0F172A),
        facingRight = true
    )

    // 2. Muscular Torso, Mechanic Vest & Safety Lapels
    val torsoY = 96f + bY

    // Broad Muscular Vest
    val vest = Path().apply {
        moveTo(98f, torsoY + 16f)
        cubicTo(120f, torsoY + 12f, 200f, torsoY + 12f, 222f, torsoY + 16f)
        lineTo(210f, torsoY + 130f)
        lineTo(110f, torsoY + 130f)
        close()
    }
    drawPath(
        vest,
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFF334155), Rogue3dPalette.VestDark, Rogue3dPalette.VestShadow),
            center = Offset(150f, torsoY + 40f),
            radius = 110f
        ),
        style = Fill
    )

    // White Chest Blaze peek at center neck
    val chestBlaze = Path().apply {
        moveTo(148f, torsoY + 14f)
        lineTo(172f, torsoY + 14f)
        lineTo(164f, torsoY + 46f)
        lineTo(156f, torsoY + 46f)
        close()
    }
    drawPath(chestBlaze, color = Rogue3dPalette.FurWhite, style = Fill)

    // Safety Yellow Folded Lapels
    val leftLapel = Path().apply {
        moveTo(134f, torsoY + 16f)
        lineTo(152f, torsoY + 16f)
        lineTo(142f, torsoY + 70f)
        lineTo(124f, torsoY + 60f)
        close()
    }
    drawPath(leftLapel, brush = Brush.linearGradient(colors = listOf(Rogue3dPalette.LapelYellow, Color(0xFFCA8A04)), start = Offset(134f, torsoY), end = Offset(142f, torsoY + 70f)), style = Fill)

    val rightLapel = Path().apply {
        moveTo(186f, torsoY + 16f)
        lineTo(168f, torsoY + 16f)
        lineTo(178f, torsoY + 70f)
        lineTo(196f, torsoY + 60f)
        close()
    }
    drawPath(rightLapel, brush = Brush.linearGradient(colors = listOf(Rogue3dPalette.LapelYellow, Color(0xFFCA8A04)), start = Offset(186f, torsoY), end = Offset(178f, torsoY + 70f)), style = Fill)

    // Muscular Bare Arms (Golden Tan with Brindle Stripes)
    val leftArm = Path().apply {
        moveTo(98f, torsoY + 16f)
        cubicTo(74f, torsoY + 60f, 85f, torsoY + 95f, 106f, torsoY + 114f)
        lineTo(120f, torsoY + 104f)
        lineTo(104f, torsoY + 68f)
        lineTo(114f, torsoY + 24f)
        close()
    }
    draw3dCylinder(leftArm, Rogue3dPalette.FurMid, Rogue3dPalette.FurLight, Rogue3dPalette.FurShadow, isLeftLimb = true)
    draw3dSphere(Offset(108f, torsoY + 118f), radius = 10f, baseColor = Rogue3dPalette.FurMid, highlightColor = Rogue3dPalette.FurLight, shadowColor = Rogue3dPalette.FurShadow)

    // Right Arm Holding Industrial Pipe Wrench
    val rightArm = Path().apply {
        moveTo(222f, torsoY + 16f)
        lineTo(244f, torsoY + 68f)
        lineTo(228f, torsoY + 114f)
        lineTo(210f, torsoY + 104f)
        lineTo(220f, torsoY + 68f)
        lineTo(208f, torsoY + 24f)
        close()
    }
    draw3dCylinder(rightArm, Rogue3dPalette.FurMid, Rogue3dPalette.FurLight, Rogue3dPalette.FurShadow, isLeftLimb = false)
    draw3dSphere(Offset(222f, torsoY + 118f), radius = 10f, baseColor = Rogue3dPalette.FurMid, highlightColor = Rogue3dPalette.FurLight, shadowColor = Rogue3dPalette.FurShadow)

    // 3D Industrial Steel Pipe Wrench
    val wrenchX = 226f
    val wrenchY = torsoY + 80f
    // Red Handle
    drawRoundRect(
        brush = Brush.horizontalGradient(
            colors = listOf(Color(0xFFEF4444), Rogue3dPalette.WrenchRed, Color(0xFF991B1B)),
            startX = wrenchX - 4f,
            endX = wrenchX + 4f
        ),
        topLeft = Offset(wrenchX - 4f, wrenchY + 20f),
        size = Size(8f, 48f),
        cornerRadius = CornerRadius(3f, 3f)
    )
    // Steel Jaws & Knurled Nut
    drawRoundRect(
        brush = Brush.horizontalGradient(
            colors = listOf(Color(0xFFE2E8F0), Rogue3dPalette.WrenchSteel, Color(0xFF475569)),
            startX = wrenchX - 10f,
            endX = wrenchX + 10f
        ),
        topLeft = Offset(wrenchX - 8f, wrenchY),
        size = Size(16f, 22f),
        cornerRadius = CornerRadius(2f, 2f)
    )

    // 3. Purple Studded 3D Collar
    drawRoundRect(
        brush = Brush.horizontalGradient(
            colors = listOf(Color(0xFFA78BFA), Rogue3dPalette.CollarPurple, Color(0xFF4C1D95)),
            startX = 120f,
            endX = 200f
        ),
        topLeft = Offset(120f, torsoY + 10f),
        size = Size(80f, 10f),
        cornerRadius = CornerRadius(4f, 4f)
    )
    for (s in 0..4) {
        val sx = 128f + s * 16f
        draw3dSphere(Offset(sx, torsoY + 15f), radius = 3.5f, baseColor = Hd3dPalettes.SilverMid, highlightColor = Hd3dPalettes.SilverLight, shadowColor = Hd3dPalettes.SilverDark)
    }

    // 4. Broad Staffy Head, Brindle Fur & Forehead Goggles
    val headY = 18f + bY

    // Folded Rose Ears
    val leftEar = Path().apply { moveTo(114f, headY + 34f); lineTo(92f, headY + 18f); lineTo(124f, headY + 16f); close() }
    drawPath(leftEar, color = Rogue3dPalette.FurShadow, style = Fill)
    val rightEar = Path().apply { moveTo(206f, headY + 34f); lineTo(228f, headY + 18f); lineTo(196f, headY + 16f); close() }
    drawPath(rightEar, color = Rogue3dPalette.FurShadow, style = Fill)

    // Broad Skull
    val head = Path().apply {
        moveTo(112f, headY + 28f)
        cubicTo(124f, headY + 10f, 196f, headY + 10f, 208f, headY + 28f)
        cubicTo(226f, headY + 54f, 224f, headY + 80f, 204f, headY + 88f)
        lineTo(160f, headY + 90f)
        lineTo(116f, headY + 88f)
        cubicTo(96f, headY + 80f, 94f, headY + 54f, 112f, headY + 28f)
        close()
    }
    drawPath(
        head,
        brush = Brush.radialGradient(
            colors = listOf(Rogue3dPalette.FurLight, Rogue3dPalette.FurMid, Rogue3dPalette.FurShadow),
            center = Offset(150f, headY + 45f),
            radius = 65f
        ),
        style = Fill
    )

    // Brindle Tiger Stripes on Forehead & Cheeks
    for (st in 0..2) {
        val sy = headY + 28f + st * 10f
        drawLine(Rogue3dPalette.FurBrindle, Offset(124f, sy), Offset(138f, sy + 4f), strokeWidth = 2.5f)
        drawLine(Rogue3dPalette.FurBrindle, Offset(196f, sy), Offset(182f, sy + 4f), strokeWidth = 2.5f)
    }

    // 3D Racing/Welding Goggles on Forehead
    drawRoundRect(
        brush = Brush.radialGradient(colors = listOf(Color(0xFF334155), Rogue3dPalette.GoggleLens), center = Offset(136f, headY + 26f), radius = 18f),
        topLeft = Offset(120f, headY + 18f),
        size = Size(34f, 16f),
        cornerRadius = CornerRadius(5f, 5f)
    )
    drawRoundRect(
        brush = Brush.radialGradient(colors = listOf(Color(0xFF334155), Rogue3dPalette.GoggleLens), center = Offset(184f, headY + 26f), radius = 18f),
        topLeft = Offset(166f, headY + 18f),
        size = Size(34f, 16f),
        cornerRadius = CornerRadius(5f, 5f)
    )
    drawLine(Hd3dPalettes.GoldMid, Offset(154f, headY + 26f), Offset(166f, headY + 26f), strokeWidth = 2.5f)

    // Wide Muscular Staffy Muzzle & Nose
    val muzzle = Path().apply {
        moveTo(132f, headY + 54f)
        lineTo(188f, headY + 54f)
        cubicTo(184f, headY + 80f, 174f, headY + 88f, 160f, headY + 90f)
        cubicTo(146f, headY + 88f, 136f, headY + 80f, 132f, headY + 54f)
        close()
    }
    drawPath(
        muzzle,
        brush = Brush.radialGradient(
            colors = listOf(Rogue3dPalette.FurLight, Rogue3dPalette.FurMid, Rogue3dPalette.FurShadow),
            center = Offset(160f, headY + 68f),
            radius = 35f
        ),
        style = Fill
    )
    draw3dSphere(Offset(160f, headY + 64f), radius = 6f, baseColor = Color(0xFF1E293B), highlightColor = Color(0xFF475569), shadowColor = Color(0xFF05080E))

    // Eyes
    if (!isBlinking) {
        draw3dEye(center = Offset(136f, headY + 48f), radius = 6f, irisColor = Color(0xFF92400E), irisGlowColor = Color(0xFFD97706))
        draw3dEye(center = Offset(184f, headY + 48f), radius = 6f, irisColor = Color(0xFF92400E), irisGlowColor = Color(0xFFD97706))
    }
}

fun DrawScope.drawRogueAvatarPortrait(isBlinking: Boolean) {
    // 320x320 64-Bit HD 3D Portrait View (Goggles, Brindle Markings, Purple Spiked Collar, Wrench)
    val head = Path().apply {
        moveTo(80f, 80f); cubicTo(110f, 30f, 210f, 30f, 240f, 80f); cubicTo(270f, 140f, 260f, 210f, 230f, 230f); lineTo(160f, 235f); lineTo(90f, 230f); cubicTo(60f, 210f, 50f, 140f, 80f, 80f); close()
    }
    drawPath(head, brush = Brush.radialGradient(colors = listOf(Rogue3dPalette.FurLight, Rogue3dPalette.FurMid, Rogue3dPalette.FurShadow), center = Offset(150f, 120f), radius = 130f), style = Fill)

    // Goggles on forehead
    drawRoundRect(brush = Brush.radialGradient(colors = listOf(Color(0xFF334155), Rogue3dPalette.GoggleLens), center = Offset(115f, 95f), radius = 35f), topLeft = Offset(85f, 75f), size = Size(65f, 35f), cornerRadius = CornerRadius(10f, 10f))
    drawRoundRect(brush = Brush.radialGradient(colors = listOf(Color(0xFF334155), Rogue3dPalette.GoggleLens), center = Offset(205f, 95f), radius = 35f), topLeft = Offset(170f, 75f), size = Size(65f, 35f), cornerRadius = CornerRadius(10f, 10f))

    // Muzzle & Nose
    draw3dSphere(Offset(160f, 175f), radius = 35f, baseColor = Rogue3dPalette.FurMid, highlightColor = Rogue3dPalette.FurLight, shadowColor = Rogue3dPalette.FurShadow)
    draw3dSphere(Offset(160f, 160f), radius = 12f, baseColor = Color(0xFF1E293B), highlightColor = Color(0xFF475569), shadowColor = Color(0xFF05080E))

    // Eyes
    if (!isBlinking) {
        draw3dEye(center = Offset(115f, 135f), radius = 13f, irisColor = Color(0xFF92400E), irisGlowColor = Color(0xFFD97706))
        draw3dEye(center = Offset(205f, 135f), radius = 13f, irisColor = Color(0xFF92400E), irisGlowColor = Color(0xFFD97706))
    }

    // Purple Studded Collar
    drawRoundRect(brush = Brush.horizontalGradient(colors = listOf(Color(0xFFA78BFA), Rogue3dPalette.CollarPurple, Color(0xFF4C1D95)), startX = 70f, endX = 250f), topLeft = Offset(70f, 220f), size = Size(180f, 24f), cornerRadius = CornerRadius(8f, 8f))
    for (s in 0..5) {
        val sx = 85f + s * 30f
        draw3dSphere(Offset(sx, 232f), radius = 7f, baseColor = Hd3dPalettes.SilverMid, highlightColor = Hd3dPalettes.SilverLight, shadowColor = Hd3dPalettes.SilverDark)
    }

    // Vest & Yellow Lapels
    val vest = Path().apply { moveTo(40f, 320f); lineTo(280f, 320f); lineTo(250f, 245f); lineTo(70f, 245f); close() }
    drawPath(vest, brush = Brush.radialGradient(colors = listOf(Color(0xFF334155), Rogue3dPalette.VestDark, Rogue3dPalette.VestShadow), center = Offset(160f, 275f), radius = 120f), style = Fill)
}
