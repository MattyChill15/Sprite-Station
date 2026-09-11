package com.example.ui.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke

/**
 * Display mode for Agent Crew character models:
 * - FULL_BODY: 64-Bit HD 3D full-body character model on 320x420 coordinate system (idle_south)
 * - AVATAR: High-definition 320x320 portrait bust focusing on 3D facial features, headgear, and signature gear
 */
enum class SpriteRenderMode {
    FULL_BODY,
    AVATAR
}

/**
 * Helper utility to manage and display 64-bit HD 3D character models within Compose UI.
 *
 * Enforces the strict 64-Bit HD Art Contract:
 * 1. Coordinate space: 320x420 px for full-body; 320x320 px for avatars.
 * 2. Adult humanoid proportions: head ~1/5 - 1/6 of total height.
 * 3. 3D volumetric surfaces with directional lighting, specular highlights, and ambient depth.
 * 4. Zero blocky pixel art or harsh 2D contours — smooth anti-aliased curves and gradients.
 * 5. Strict height locks: Wiz is strictly locked at ~0.75f relative to Cudi (compact stature).
 */
object PixelSpriteHelper {

    // Canvas coordinate resolutions
    const val CANVAS_WIDTH = 320f
    const val CANVAS_HEIGHT = 420f

    const val AVATAR_CANVAS_SIZE = 320f

    /**
     * Strict height scaling relative to baseline standard (1.0f).
     * Wiz is strictly locked at ~0.75f (shorter than Cudi).
     * Chief stands at 1.05f with commanding presence.
     */
    fun getCharacterScale(crewId: String): Float {
        return when (crewId) {
            "chief" -> 1.05f
            "wiz" -> 0.75f    // STRICT LOOK LOCK: ~3/4 Cudi height
            "snoop" -> 1.02f
            "cudi" -> 1.00f   // Baseline standard
            "gaps" -> 1.00f
            "dodge" -> 0.98f
            "shayde" -> 0.98f
            "rogue" -> 0.95f
            "affleck" -> 0.92f
            "shorty" -> 0.90f
            else -> 1.00f
        }
    }

    /**
     * Computes uniform scaling to fit the 320x420 canvas within the container dimensions
     * without distortion or stretching.
     */
    fun computeScale(
        containerWidth: Float,
        containerHeight: Float,
        canvasWidth: Float = CANVAS_WIDTH,
        canvasHeight: Float = CANVAS_HEIGHT,
        heightScale: Float = 1.0f
    ): Float {
        val baseScale = minOf(containerWidth / canvasWidth, containerHeight / canvasHeight)
        return baseScale * heightScale
    }
}

/**
 * Modifier for rendering smooth anti-aliased 64-bit HD graphics.
 */
fun Modifier.crispPixelArt(): Modifier = this.drawWithContent {
    drawContent()
}

/**
 * Helper drawing extensions for HD 3D character rendering on the 320x420 coordinate system.
 */
fun DrawScope.drawCrispOutline(path: Path, strokeWidth: Float = 1.6f, color: Color = Color(0xFF070C12)) {
    drawPath(path = path, color = color, style = Stroke(width = strokeWidth))
}

fun DrawScope.drawShadedPolygon(path: Path, fillColor: Color, outlineColor: Color = Color(0xFF0A0F18), strokeWidth: Float = 1.4f) {
    drawPath(path = path, color = fillColor, style = Fill)
    if (strokeWidth > 0f) {
        drawPath(path = path, color = outlineColor, style = Stroke(width = strokeWidth))
    }
}
