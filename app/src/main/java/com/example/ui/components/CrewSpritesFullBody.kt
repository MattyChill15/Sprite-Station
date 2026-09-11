package com.example.ui.components

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.example.model.CrewMember
import com.example.ui.components.sprites.drawAffleckFullBody
import com.example.ui.components.sprites.drawChiefFullBody
import com.example.ui.components.sprites.drawCudiFullBody
import com.example.ui.components.sprites.drawDodgeFullBody
import com.example.ui.components.sprites.drawGapsFullBody
import com.example.ui.components.sprites.drawRogueFullBody
import com.example.ui.components.sprites.drawShaydeFullBody
import com.example.ui.components.sprites.drawShortyFullBody
import com.example.ui.components.sprites.drawSnoopFullBody
import com.example.ui.components.sprites.drawWizFullBody

/**
 * 64-Bit HD 3D Character Renderer for the Hot-10 Crew.
 * Canvas Coordinate Resolution: 320px width x 420px height.
 *
 * Star Fox 64-Adjacent High Definition 3D Aesthetic:
 * 1. 3D volumetric surfaces with directional lighting, specular highlights, and ambient depth.
 * 2. Adult humanoid proportions with smooth curved forms (zero 2D pixel blocks, zero harsh 2px outlines).
 * 3. Directional Key Light from Top-Left, Core Shadow on Lower-Right, soft underfoot contact shadows.
 * 4. Distinctive 3D personality gear (Chief's raptor beak & phone, Shorty's backward cap & headphones,
 *    Rogue's goggles & steel pipe wrench, Affleck's silver Cuban chain, Dodge's tactical boom mic,
 *    Snoop's dreadlock beads & MA-1 bomber, Wiz's satchel & aviators, Cudi's crescent moon,
 *    Gaps' spectacles & clipboard, Shayde's glowing violet stealth armor).
 */
fun DrawScope.drawCrewFullBody(
    crew: CrewMember,
    breathe: Float,
    walkFrame: Int,
    isWalking: Boolean,
    isBlinking: Boolean
) {
    // Idle breathing micro-shift applied to torso and upper body (smooth organic vertical shift)
    val bY = if (!isWalking) breathe * 2.0f else 0f
    val walkOffset = if (isWalking) (walkFrame % 2) * 2.5f else 0f

    when (crew.id) {
        "chief" -> drawChiefFullBody(bY, walkOffset, isWalking, isBlinking)
        "rogue" -> drawRogueFullBody(bY, walkOffset, isWalking, isBlinking)
        "affleck" -> drawAffleckFullBody(bY, walkOffset, isWalking, isBlinking)
        "dodge" -> drawDodgeFullBody(bY, walkOffset, isWalking, isBlinking)
        "snoop" -> drawSnoopFullBody(bY, walkOffset, isWalking, isBlinking)
        "wiz" -> drawWizFullBody(bY, walkOffset, isWalking, isBlinking)
        "cudi" -> drawCudiFullBody(bY, walkOffset, isWalking, isBlinking)
        "shorty" -> drawShortyFullBody(bY, walkOffset, isWalking, isBlinking)
        "gaps" -> drawGapsFullBody(bY, walkOffset, isWalking, isBlinking)
        "shayde" -> drawShaydeFullBody(bY, walkOffset, isWalking, isBlinking)
        else -> drawDefaultHD(bY)
    }
}

private fun DrawScope.drawDefaultHD(bY: Float) {
    drawRoundRect(
        color = Color(0xFF00E5FF),
        topLeft = Offset(120f, 100f + bY),
        size = Size(80f, 120f),
        cornerRadius = CornerRadius(12f, 12f)
    )
}
