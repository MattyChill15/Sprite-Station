package com.example.ui.components

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.model.CrewMember
import com.example.ui.components.sprites.drawAffleckAvatarPortrait
import com.example.ui.components.sprites.drawChiefAvatarPortrait
import com.example.ui.components.sprites.drawCudiAvatarPortrait
import com.example.ui.components.sprites.drawDodgeAvatarPortrait
import com.example.ui.components.sprites.drawGapsAvatarPortrait
import com.example.ui.components.sprites.drawRogueAvatarPortrait
import com.example.ui.components.sprites.drawShaydeAvatarPortrait
import com.example.ui.components.sprites.drawShortyAvatarPortrait
import com.example.ui.components.sprites.drawSnoopAvatarPortrait
import com.example.ui.components.sprites.drawWizAvatarPortrait

/**
 * 64-Bit HD 3D Portrait Avatars for the Hot-10 Crew.
 * Canvas Coordinate Resolution: 320px x 320px.
 *
 * Star Fox 64-Adjacent High Definition 3D Aesthetic:
 * - Sculpted 3D facial forms with directional lighting and specular reflections.
 * - Organic curves, smooth shading, volumetric gear, and zero blocky pixel art.
 */
fun DrawScope.drawCrewAvatar(
    crew: CrewMember,
    isBlinking: Boolean
) {
    // Tactical circular background glow disc
    drawCircle(
        color = Color(0x2200E5FF),
        center = Offset(160f, 160f),
        radius = 145f
    )
    drawCircle(
        color = Color(0x4400E5FF),
        center = Offset(160f, 160f),
        radius = 145f,
        style = Stroke(width = 3f)
    )

    when (crew.id) {
        "chief" -> drawChiefAvatarPortrait(isBlinking)
        "rogue" -> drawRogueAvatarPortrait(isBlinking)
        "affleck" -> drawAffleckAvatarPortrait(isBlinking)
        "dodge" -> drawDodgeAvatarPortrait(isBlinking)
        "snoop" -> drawSnoopAvatarPortrait(isBlinking)
        "wiz" -> drawWizAvatarPortrait(isBlinking)
        "cudi" -> drawCudiAvatarPortrait(isBlinking)
        "shorty" -> drawShortyAvatarPortrait(isBlinking)
        "gaps" -> drawGapsAvatarPortrait(isBlinking)
        "shayde" -> drawShaydeAvatarPortrait(isBlinking)
        else -> drawDefaultAvatarHD()
    }
}

private fun DrawScope.drawDefaultAvatarHD() {
    drawRoundRect(
        color = Color(0xFF00E5FF),
        topLeft = Offset(80f, 80f),
        size = Size(160f, 160f),
        cornerRadius = CornerRadius(24f, 24f)
    )
}
