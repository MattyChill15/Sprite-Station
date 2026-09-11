package com.example.ui.components.sprites

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke

/**
 * 64-Bit HD 3D Volumetric Primitives and Material Shaders.
 * Star Fox 64-adjacent High-Definition 3D Anthropomorphic Character Rendering.
 *
 * Core 64-bit HD Principles:
 * - NO flat 2D pixel blocks or harsh 2px black contour outlines.
 * - True 3D volumetric surfaces modeled with directional lighting (Key light from Top-Left,
 *   Core shadow on Lower-Right, Soft ambient bounce light).
 * - Radial and linear gradient shaders for spherical heads, cylindrical limbs, and curved gear.
 * - Specular gloss spots on eyes, metallics (gold rings, brass buckles, chains, wrench), and leathers.
 * - Soft underfoot ambient occlusion drop shadow with radial alpha falloff.
 * - Smooth anti-aliased organic curves and sculpted 3D volume.
 */
object Hd3dPalettes {
    val SpecularWhite = Color(0xFFFFFFFF)
    val RimLightCyan = Color(0x6600E5FF)
    val RimLightWarm = Color(0x44FEF08A)
    val AmbientShadow = Color(0x7705080E)
    val DeepCrevice = Color(0xFF070B12)

    // Metals
    val GoldLight = Color(0xFFFDE047)
    val GoldMid = Color(0xFFEAB308)
    val GoldDark = Color(0xFF854D0E)

    val SilverLight = Color(0xFFF8FAFC)
    val SilverMid = Color(0xFF94A3B8)
    val SilverDark = Color(0xFF334155)

    val SteelLight = Color(0xFFCBD5E1)
    val SteelDark = Color(0xFF1E293B)
}

/**
 * Renders a smooth 3D contact shadow on the ground floor or duty pad.
 */
fun DrawScope.draw3dContactShadow(
    centerX: Float,
    groundY: Float,
    radiusX: Float = 60f,
    radiusY: Float = 14f,
    alpha: Float = 0.55f
) {
    drawOval(
        brush = Brush.radialGradient(
            colors = listOf(
                Color(0x00000000).copy(alpha = alpha),
                Color(0x00000000).copy(alpha = alpha * 0.4f),
                Color(0x00000000)
            ),
            center = Offset(centerX, groundY),
            radius = radiusX
        ),
        topLeft = Offset(centerX - radiusX, groundY - radiusY),
        size = Size(radiusX * 2f, radiusY * 2f)
    )
}

/**
 * Draws a volumetric 3D path with directional linear gradient and soft ambient depth.
 */
fun DrawScope.drawVolumetricShape(
    path: Path,
    lightColor: Color,
    midColor: Color,
    shadowColor: Color,
    startOffset: Offset = Offset(100f, 60f),
    endOffset: Offset = Offset(220f, 320f),
    rimHighlight: Color? = null,
    ambientDepthWidth: Float = 1.2f
) {
    // 3D Volumetric Gradient Fill
    val brush = Brush.linearGradient(
        colors = listOf(lightColor, midColor, shadowColor),
        start = startOffset,
        end = endOffset
    )
    drawPath(path, brush = brush, style = Fill)

    // Soft Ambient Depth Rim Line (not a harsh black box line, but smooth lighting falloff)
    if (ambientDepthWidth > 0f) {
        val rimCol = shadowColor.copy(alpha = 0.6f)
        drawPath(
            path,
            color = rimCol,
            style = Stroke(width = ambientDepthWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)
        )
    }

    rimHighlight?.let {
        drawPath(
            path,
            brush = Brush.linearGradient(
                colors = listOf(it, Color.Transparent),
                start = startOffset,
                end = Offset((startOffset.x + endOffset.x) * 0.5f, (startOffset.y + endOffset.y) * 0.5f)
            ),
            style = Stroke(width = 1.5f, cap = StrokeCap.Round, join = StrokeJoin.Round)
        )
    }
}

/**
 * Draws a 3D volumetric cylinder for limbs (arms, legs, torso segments).
 */
fun DrawScope.draw3dCylinder(
    path: Path,
    baseColor: Color,
    highlightColor: Color,
    shadowColor: Color,
    isLeftLimb: Boolean = true
) {
    val startX = if (isLeftLimb) 80f else 170f
    val endX = if (isLeftLimb) 150f else 240f

    val brush = Brush.linearGradient(
        colors = listOf(highlightColor, baseColor, shadowColor, shadowColor.copy(alpha = 0.8f)),
        start = Offset(startX, 0f),
        end = Offset(endX, 0f)
    )
    drawPath(path, brush = brush, style = Fill)

    // Subtle 3D edge depth
    drawPath(
        path,
        color = shadowColor.copy(alpha = 0.5f),
        style = Stroke(width = 1.2f, cap = StrokeCap.Round, join = StrokeJoin.Round)
    )
}

/**
 * Draws a 3D volumetric sphere (for snouts, cheeks, buttons, metallic spheres).
 */
fun DrawScope.draw3dSphere(
    center: Offset,
    radius: Float,
    baseColor: Color,
    highlightColor: Color,
    shadowColor: Color,
    hasSpecular: Boolean = true
) {
    val lightCenter = Offset(center.x - radius * 0.35f, center.y - radius * 0.35f)
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(highlightColor, baseColor, shadowColor),
            center = lightCenter,
            radius = radius * 1.25f
        ),
        radius = radius,
        center = center
    )

    if (hasSpecular) {
        // Specular glint
        drawCircle(
            color = Hd3dPalettes.SpecularWhite.copy(alpha = 0.75f),
            radius = radius * 0.22f,
            center = lightCenter
        )
    }
}

/**
 * Draws a 3D volumetric eye with spherical cornea, 3D iris depth, pupil, and dual specular gloss.
 */
fun DrawScope.draw3dEye(
    center: Offset,
    radius: Float,
    irisColor: Color,
    irisGlowColor: Color = irisColor,
    isSlitPupil: Boolean = false,
    eyelinerColor: Color = Color(0xFF0F172A)
) {
    // Sclera / eye contour with 3D shadow under brow
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFFFFFFFF), Color(0xFFE2E8F0), Color(0xFF94A3B8)),
            center = Offset(center.x, center.y + radius * 0.2f),
            radius = radius * 1.1f
        ),
        radius = radius,
        center = center
    )
    // Eyeliner / socket depth
    drawCircle(
        color = eyelinerColor,
        radius = radius,
        center = center,
        style = Stroke(width = 1.4f)
    )

    // 3D Iris with glowing depth
    val irisRadius = radius * 0.72f
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(irisGlowColor, irisColor, eyelinerColor),
            center = Offset(center.x - irisRadius * 0.2f, center.y - irisRadius * 0.2f),
            radius = irisRadius * 1.1f
        ),
        radius = irisRadius,
        center = center
    )

    // Pupil
    if (isSlitPupil) {
        // Vertical feline slit pupil with 3D tapering
        val pupil = Path().apply {
            moveTo(center.x, center.y - irisRadius * 0.85f)
            cubicTo(center.x + 2f, center.y, center.x + 2f, center.y, center.x, center.y + irisRadius * 0.85f)
            cubicTo(center.x - 2f, center.y, center.x - 2f, center.y, center.x, center.y - irisRadius * 0.85f)
            close()
        }
        drawPath(pupil, color = Color(0xFF05080E), style = Fill)
    } else {
        drawCircle(
            color = Color(0xFF05080E),
            radius = irisRadius * 0.45f,
            center = center
        )
    }

    // Dual 3D Specular Highlights (Key light + bounce light)
    drawCircle(
        color = Hd3dPalettes.SpecularWhite.copy(alpha = 0.9f),
        radius = irisRadius * 0.28f,
        center = Offset(center.x - irisRadius * 0.35f, center.y - irisRadius * 0.35f)
    )
    drawCircle(
        color = Hd3dPalettes.SpecularWhite.copy(alpha = 0.5f),
        radius = irisRadius * 0.14f,
        center = Offset(center.x + irisRadius * 0.28f, center.y + irisRadius * 0.28f)
    )
}

/**
 * 64-Bit HD 3D Sculpted Retro High-Top Sneaker (Jordan 1 / Dunk High silhouette).
 * Fully volumetric with 3D leather panels, beveled rubber midsole, padded collar, and tread depth.
 */
fun DrawScope.draw3dHighTopSneaker(
    x: Float,
    y: Float,
    width: Float = 58f,
    height: Float = 36f,
    primaryLight: Color,
    primaryMid: Color,
    primaryDark: Color,
    accentColor: Color = Color(0xFFF8FAFC),
    facingRight: Boolean = true
) {
    val dir = if (facingRight) 1f else -1f
    val startX = if (facingRight) x else x + width

    // 1. Volumetric Padded Ankle Collar
    val collar = Path().apply {
        moveTo(startX + 6f * dir, y)
        cubicTo(startX + 20f * dir, y - 2f, startX + 30f * dir, y, startX + 34f * dir, y + 2f)
        lineTo(startX + 32f * dir, y + 18f)
        lineTo(startX + 4f * dir, y + 18f)
        close()
    }
    drawPath(
        collar,
        brush = Brush.linearGradient(
            colors = listOf(primaryLight, primaryMid, primaryDark),
            start = Offset(startX, y),
            end = Offset(startX + 34f * dir, y + 18f)
        ),
        style = Fill
    )
    // Collar indentation padding
    drawCircle(
        color = primaryDark.copy(alpha = 0.6f),
        radius = 4f,
        center = Offset(startX + 18f * dir, y + 6f)
    )

    // 2. Sculpted Heel Counter
    val heel = Path().apply {
        moveTo(startX + 4f * dir, y + 16f)
        lineTo(startX + 22f * dir, y + 16f)
        lineTo(startX + 20f * dir, y + 29f)
        lineTo(startX - 2f * dir, y + 29f)
        close()
    }
    drawPath(
        heel,
        brush = Brush.linearGradient(
            colors = listOf(primaryMid, primaryDark),
            start = Offset(startX, y + 16f),
            end = Offset(startX + 22f * dir, y + 29f)
        ),
        style = Fill
    )

    // 3. Volumetric Midfoot & Vamp (White/Accent Leather)
    val midfoot = Path().apply {
        moveTo(startX + 18f * dir, y + 16f)
        lineTo(startX + 48f * dir, y + 16f)
        cubicTo(startX + 56f * dir, y + 22f, startX + 56f * dir, y + 26f, startX + 54f * dir, y + 29f)
        lineTo(startX + 16f * dir, y + 29f)
        close()
    }
    drawPath(
        midfoot,
        brush = Brush.linearGradient(
            colors = listOf(accentColor, accentColor.copy(alpha = 0.9f), Color(0xFFCBD5E1)),
            start = Offset(startX + 18f * dir, y + 16f),
            end = Offset(startX + 54f * dir, y + 29f)
        ),
        style = Fill
    )

    // 4. Rounded 3D Mudguard & Curved Toe Cap
    val toeCap = Path().apply {
        moveTo(startX + 36f * dir, y + 19f)
        cubicTo(startX + 46f * dir, y + 18f, startX + 54f * dir, y + 21f, startX + 58f * dir, y + 27f)
        lineTo(startX + 38f * dir, y + 28f)
        close()
    }
    drawPath(
        toeCap,
        brush = Brush.linearGradient(
            colors = listOf(primaryLight, primaryMid, primaryDark),
            start = Offset(startX + 36f * dir, y + 19f),
            end = Offset(startX + 58f * dir, y + 28f)
        ),
        style = Fill
    )

    // 5. 3D Side Swoop / Chevron with Specular Bevel
    val swoop = Path().apply {
        moveTo(startX + 16f * dir, y + 20f)
        cubicTo(startX + 28f * dir, y + 22f, startX + 36f * dir, y + 21f, startX + 42f * dir, y + 24f)
        lineTo(startX + 38f * dir, y + 27f)
        cubicTo(startX + 26f * dir, y + 25f, startX + 18f * dir, y + 24f, startX + 14f * dir, y + 23f)
        close()
    }
    drawPath(
        swoop,
        brush = Brush.linearGradient(
            colors = listOf(primaryLight, primaryDark),
            start = Offset(startX + 16f * dir, y + 20f),
            end = Offset(startX + 42f * dir, y + 27f)
        ),
        style = Fill
    )

    // 6. Sculpted 3D Laces with Highlights
    for (l in 0..3) {
        val ly = y + 7f + l * 4f
        val lx = startX + (22f + l * 4.5f) * dir
        drawLine(
            brush = Brush.linearGradient(
                colors = listOf(Color(0xFFFFFFFF), Color(0xFFE2E8F0)),
                start = Offset(lx, ly),
                end = Offset(lx + 8f * dir, ly + 2f)
            ),
            start = Offset(lx, ly),
            end = Offset(lx + 8f * dir, ly + 2f),
            strokeWidth = 2.4f,
            cap = StrokeCap.Round
        )
    }

    // 7. Sculpted Rubber Midsole (Smooth 3D Bevel)
    val midL = if (facingRight) startX - 3f else startX - width - 2f
    val midW = width + 5f
    drawRoundRect(
        brush = Brush.verticalGradient(
            colors = listOf(Color(0xFFFFFFFF), Color(0xFFF1F5F9), Color(0xFFCBD5E1)),
            startY = y + 27f,
            endY = y + 34f
        ),
        topLeft = Offset(midL, y + 27f),
        size = Size(midW, 7.5f),
        cornerRadius = CornerRadius(3f, 3f)
    )

    // 8. 3D Outsole Tread Layer
    drawRoundRect(
        brush = Brush.verticalGradient(
            colors = listOf(primaryMid, primaryDark, Color(0xFF0F172A)),
            startY = y + 33f,
            endY = y + 37f
        ),
        topLeft = Offset(midL + 1f, y + 33f),
        size = Size(midW - 2f, 4.5f),
        cornerRadius = CornerRadius(2f, 2f)
    )
}

/**
 * 64-Bit HD 3D Heavy Lugged Combat / Work Boot (Rogue, Affleck, Dodge).
 */
fun DrawScope.draw3dCombatBoot(
    x: Float,
    y: Float,
    width: Float = 56f,
    height: Float = 36f,
    leatherLight: Color,
    leatherMid: Color,
    leatherDark: Color,
    facingRight: Boolean = true
) {
    val dir = if (facingRight) 1f else -1f
    val startX = if (facingRight) x else x + width

    // Shaft
    val shaft = Path().apply {
        moveTo(startX + 4f * dir, y)
        lineTo(startX + 30f * dir, y + 2f)
        lineTo(startX + 28f * dir, y + 20f)
        lineTo(startX + 2f * dir, y + 20f)
        close()
    }
    drawPath(
        shaft,
        brush = Brush.linearGradient(
            colors = listOf(leatherLight, leatherMid, leatherDark),
            start = Offset(startX, y),
            end = Offset(startX + 30f * dir, y + 20f)
        ),
        style = Fill
    )

    // Foot
    val foot = Path().apply {
        moveTo(startX + 2f * dir, y + 18f)
        lineTo(startX + 48f * dir, y + 18f)
        cubicTo(startX + 56f * dir, y + 22f, startX + 56f * dir, y + 26f, startX + 54f * dir, y + 29f)
        lineTo(startX - 2f * dir, y + 29f)
        close()
    }
    drawPath(
        foot,
        brush = Brush.linearGradient(
            colors = listOf(leatherLight, leatherMid, leatherDark),
            start = Offset(startX, y + 18f),
            end = Offset(startX + 54f * dir, y + 29f)
        ),
        style = Fill
    )

    // Steel Toe Cap Specular Reflection
    val toe = Path().apply {
        moveTo(startX + 38f * dir, y + 19f)
        cubicTo(startX + 48f * dir, y + 19f, startX + 55f * dir, y + 23f, startX + 56f * dir, y + 28f)
        lineTo(startX + 39f * dir, y + 28f)
        close()
    }
    drawPath(
        toe,
        brush = Brush.linearGradient(
            colors = listOf(Color(0xFF475569), Color(0xFF1E293B), Color(0xFF0F172A)),
            start = Offset(startX + 38f * dir, y + 19f),
            end = Offset(startX + 56f * dir, y + 28f)
        ),
        style = Fill
    )

    // Brass Speed Eyelets
    for (i in 0..3) {
        val ey = y + 5f + i * 4.5f
        val ex = startX + (22f + i * 4f) * dir
        draw3dSphere(Offset(ex, ey), radius = 2.2f, baseColor = Hd3dPalettes.GoldMid, highlightColor = Hd3dPalettes.GoldLight, shadowColor = Hd3dPalettes.GoldDark)
    }

    // Heavy Commando Lugged Sole with 3D Depth
    val soleL = if (facingRight) startX - 4f else startX - width - 2f
    val soleW = width + 6f
    drawRoundRect(
        brush = Brush.verticalGradient(
            colors = listOf(Color(0xFF334155), Color(0xFF1E293B), Color(0xFF090D14)),
            startY = y + 27f,
            endY = y + 36f
        ),
        topLeft = Offset(soleL, y + 27f),
        size = Size(soleW, 9f),
        cornerRadius = CornerRadius(3f, 3f)
    )
}

/**
 * Draws a 3D volumetric zipper line with metallic gradient slider and pull tab.
 */
fun DrawScope.draw3dZipper(
    x: Float,
    startY: Float,
    endY: Float,
    pullTabY: Float = startY + (endY - startY) * 0.25f,
    metallicColor: Color = Hd3dPalettes.SilverLight
) {
    // Zipper recessed tape shadow
    drawLine(
        color = Color(0x6605080E),
        start = Offset(x, startY),
        end = Offset(x, endY),
        strokeWidth = 3f,
        cap = StrokeCap.Round
    )
    // Metallic teeth highlight line
    drawLine(
        brush = Brush.verticalGradient(
            colors = listOf(metallicColor, Hd3dPalettes.SilverMid, metallicColor),
            startY = startY,
            endY = endY
        ),
        start = Offset(x, startY),
        end = Offset(x, endY),
        strokeWidth = 1.6f
    )
    // 3D Pull Slider
    drawRoundRect(
        brush = Brush.verticalGradient(
            colors = listOf(Hd3dPalettes.SilverLight, Hd3dPalettes.SilverMid, Hd3dPalettes.SilverDark),
            startY = pullTabY,
            endY = pullTabY + 10f
        ),
        topLeft = Offset(x - 3f, pullTabY),
        size = Size(6f, 10f),
        cornerRadius = CornerRadius(2.5f, 2.5f)
    )
}
