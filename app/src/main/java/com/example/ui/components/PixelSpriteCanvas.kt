package com.example.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.model.CrewMember
import com.example.ui.util.PixelSpriteHelper
import com.example.ui.util.SpriteRenderMode
import com.example.ui.util.crispPixelArt

/**
 * Procedural HD 2D Pixel Art Sprite Component for the Sprite Station Hot-10 Crew.
 *
 * Utilizes the PixelSpriteHelper utility to manage:
 * - Consistent scaling (normalized coordinate system, strict character height locks like Wiz ~3/4 Cudi)
 * - Crisp rendering with hard point-sampled pixel edges (no blurry bilinear filtering or Minecraft blockiness)
 * - Full-Body south-facing sprites (idle_south) and Portrait Avatars (avatars)
 *
 * Character look-locks:
 * - Chief: Bald eagle, white feather head, yellow hooked beak, navy officer jumpsuit with white star, sneakers.
 * - Rogue: Brindle staffy, white chest blaze, purple goggles on forehead, purple collar, yellow lining, wrench.
 * - Affleck: Boston terrier tuxedo markings, bat ears, chunky silver curb chain, hard bulldog stare.
 * - Dodge: Headset dog, black head, white blaze, tan eyebrow & cheek spots, boom mic, red-and-grey tech jacket.
 * - Snoop: Chill hound, long dreadlocks with gold beads, lightbulb beanie, boom mic, olive bomber with orange lining.
 * - Wiz: Raccoon bandit, watch beanie with aviators on rim, goatee, gold chain, satchel with notepad/pens, ~3/4 height.
 * - Cudi: Moon-dreamer wolf, navy beanie, silver crescent pendant, quilted bomber, glowing full moon backdrop.
 * - Shorty: Red fox, backward red snapback, over-ear neck headphones, red puffer vest with "OK" patch, green cargos.
 * - Gaps: Great horned owl, feathered horn tufts, round spectacles, green service uniform with badge, clipboard with checklist.
 * - Shayde: Midnight-black cat, high-tech headset with glowing purple ring, luminous purple slit eyes, moon pin, purple laces.
 */
@Composable
fun PixelSprite(
    crew: CrewMember,
    modifier: Modifier = Modifier,
    size: Dp = 96.dp,
    renderMode: SpriteRenderMode = SpriteRenderMode.FULL_BODY,
    walkFrame: Int = 0,
    isWalking: Boolean = false,
    flipX: Boolean = false,
    ambientCyanGlow: Boolean = false
) {
    // Subtle idle breathing micro-shift
    val infiniteTransition = rememberInfiniteTransition(label = "idle_breathing_${crew.id}")
    val breatheOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1400, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "breathe"
    )

    // Eye blink cycle
    val blinkTick by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3400, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "blink"
    )
    val isBlinking = blinkTick in 9.4f..9.9f

    val isFullBody = renderMode == SpriteRenderMode.FULL_BODY
    val targetWidth = size
    val targetHeight = if (isFullBody) size * (420f / 320f) else size

    Box(
        modifier = modifier
            .width(targetWidth)
            .height(targetHeight)
            .crispPixelArt(),
        contentAlignment = if (isFullBody) Alignment.BottomCenter else Alignment.Center
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val isFullBody = renderMode == SpriteRenderMode.FULL_BODY
            val canvasW = if (isFullBody) PixelSpriteHelper.CANVAS_WIDTH else PixelSpriteHelper.AVATAR_CANVAS_SIZE
            val canvasH = if (isFullBody) PixelSpriteHelper.CANVAS_HEIGHT else PixelSpriteHelper.AVATAR_CANVAS_SIZE

            val heightScale = if (isFullBody) PixelSpriteHelper.getCharacterScale(crew.id) else 1.0f
            val uniformScale = PixelSpriteHelper.computeScale(
                containerWidth = this.size.width,
                containerHeight = this.size.height,
                canvasWidth = canvasW,
                canvasHeight = canvasH,
                heightScale = heightScale
            )

            val totalSpriteWidth = canvasW * uniformScale
            val totalSpriteHeight = canvasH * uniformScale
            val originX = (this.size.width - totalSpriteWidth) / 2f
            val originY = if (isFullBody) (this.size.height - totalSpriteHeight) else (this.size.height - totalSpriteHeight) / 2f

            val scaleXFactor = if (flipX) -1f else 1f

            scale(
                scaleX = scaleXFactor,
                scaleY = 1f,
                pivot = Offset(this.size.width / 2f, if (isFullBody) this.size.height else this.size.height / 2f)
            ) {
                translate(left = originX, top = originY) {
                    scale(
                        scaleX = uniformScale,
                        scaleY = uniformScale,
                        pivot = Offset.Zero
                    ) {
                        if (isFullBody) {
                            drawCrewFullBody(
                                crew = crew,
                                breathe = if (!isWalking) breatheOffset else 0f,
                                walkFrame = walkFrame,
                                isWalking = isWalking,
                                isBlinking = isBlinking
                            )
                        } else {
                            drawCrewAvatar(
                                crew = crew,
                                isBlinking = isBlinking
                            )
                        }

                        if (ambientCyanGlow) {
                            // Soft Holotable cyan reflection across front-facing bottom edge
                            drawRect(
                                color = Color(0x1800E5FF),
                                topLeft = Offset(canvasW * 0.2f, canvasH * 0.5f),
                                size = Size(canvasW * 0.6f, canvasH * 0.45f)
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Dedicated Composable for displaying Agent portrait avatars (bust / look-lock view).
 */
@Composable
fun PixelSpriteAvatar(
    crew: CrewMember,
    modifier: Modifier = Modifier,
    size: Dp = 80.dp,
    ambientCyanGlow: Boolean = false
) {
    PixelSprite(
        crew = crew,
        modifier = modifier,
        size = size,
        renderMode = SpriteRenderMode.AVATAR,
        ambientCyanGlow = ambientCyanGlow
    )
}
