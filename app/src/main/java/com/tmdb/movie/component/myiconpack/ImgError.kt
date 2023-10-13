package com.tmdb.movie.component.myiconpack

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

fun buildErrorVector(primaryColor: Color = Color(0xFF536DFE),
                     backgroundColor: Color = Color(0xFFFFFFFF)): ImageVector {
    return Builder(
        name = "ImgError", defaultWidth = 1022.7.dp, defaultHeight = 785.81.dp,
        viewportWidth = 1022.7f, viewportHeight = 785.81f
    ).apply {
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(43.63f, 660.88f)
            arcToRelative(425.0f, 33.0f, 0.0f, true, false, 850.0f, 0.0f)
            arcToRelative(425.0f, 33.0f, 0.0f, true, false, -850.0f, 0.0f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(845.05f, 472.84f)
            curveToRelative(-2.54f, -7.71f, -12.84f, -11.26f, -23.0f, -7.92f)
            arcToRelative(24.76f, 24.76f, 0.0f, false, false, -4.23f, 1.83f)
            curveToRelative(-0.65f, -0.18f, -1.32f, -0.34f, -2.0f, -0.46f)
            arcToRelative(22.42f, 22.42f, 0.0f, false, false, 0.63f, -6.79f)
            arcTo(24.6f, 24.6f, 0.0f, false, false, 827.35f, 438.03f)
            arcToRelative(24.63f, 24.63f, 0.0f, false, false, 10.86f, -21.47f)
            arcToRelative(23.76f, 23.76f, 0.0f, false, false, 8.23f, -9.32f)
            curveToRelative(4.9f, -9.7f, 2.87f, -20.6f, -4.54f, -24.35f)
            reflectiveCurveToRelative(-17.4f, 1.08f, -22.3f, 10.78f)
            arcToRelative(23.69f, 23.69f, 0.0f, false, false, -2.63f, 12.15f)
            arcToRelative(24.63f, 24.63f, 0.0f, false, false, -10.86f, 21.47f)
            arcToRelative(24.63f, 24.63f, 0.0f, false, false, -10.86f, 21.47f)
            arcTo(24.64f, 24.64f, 0.0f, false, false, 784.35f, 470.24f)
            arcToRelative(23.76f, 23.76f, 0.0f, false, false, -8.23f, 9.32f)
            arcToRelative(25.46f, 25.46f, 0.0f, false, false, -2.08f, 5.74f)
            arcToRelative(21.18f, 21.18f, 0.0f, false, false, -4.44f, -4.73f)
            arcToRelative(25.38f, 25.38f, 0.0f, false, false, -1.0f, -4.5f)
            curveToRelative(-3.34f, -10.17f, -12.3f, -16.37f, -20.0f, -13.83f)
            reflectiveCurveToRelative(-11.26f, 12.83f, -7.92f, 23.0f)
            arcToRelative(23.07f, 23.07f, 0.0f, false, false, 7.56f, 11.08f)
            arcToRelative(25.38f, 25.38f, 0.0f, false, false, 1.0f, 4.5f)
            curveToRelative(1.94f, 5.9f, 5.77f, 10.46f, 10.15f, 12.75f)
            arcToRelative(23.23f, 23.23f, 0.0f, false, false, -0.83f, 3.9f)
            arcToRelative(25.29f, 25.29f, 0.0f, false, false, -7.54f, 14.91f)
            arcToRelative(25.27f, 25.27f, 0.0f, false, false, -7.54f, 14.92f)
            arcToRelative(24.63f, 24.63f, 0.0f, false, false, -5.0f, 6.81f)
            curveToRelative(-4.91f, 9.7f, -2.88f, 20.61f, 4.53f, 24.35f)
            reflectiveCurveToRelative(17.4f, -1.07f, 22.31f, -10.78f)
            arcToRelative(24.62f, 24.62f, 0.0f, false, false, 2.5f, -8.09f)
            arcToRelative(25.29f, 25.29f, 0.0f, false, false, 7.54f, -14.91f)
            arcToRelative(25.27f, 25.27f, 0.0f, false, false, 7.54f, -14.92f)
            arcTo(25.21f, 25.21f, 0.0f, false, false, 790.35f, 514.85f)
            arcTo(25.21f, 25.21f, 0.0f, false, false, 797.91f, 499.91f)
            arcToRelative(24.11f, 24.11f, 0.0f, false, false, 3.39f, -4.0f)
            arcToRelative(23.46f, 23.46f, 0.0f, false, false, 12.27f, -0.77f)
            arcToRelative(24.76f, 24.76f, 0.0f, false, false, 4.23f, -1.83f)
            arcToRelative(23.2f, 23.2f, 0.0f, false, false, 13.42f, -0.48f)
            curveTo(841.35f, 489.52f, 847.58f, 480.56f, 845.05f, 472.84f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(827.12f, 442.44f)
            arcToRelative(21.5f, 21.5f, 0.0f, false, true, -3.44f, 2.75f)
            arcToRelative(23.0f, 23.0f, 0.0f, false, true, -1.11f, 8.43f)
            arcToRelative(26.18f, 26.18f, 0.0f, false, false, 2.1f, -3.43f)
            arcTo(24.64f, 24.64f, 0.0f, false, false, 827.12f, 442.44f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(837.98f, 420.97f)
            arcToRelative(21.5f, 21.5f, 0.0f, false, true, -3.44f, 2.75f)
            arcToRelative(23.0f, 23.0f, 0.0f, false, true, -1.11f, 8.43f)
            arcToRelative(25.65f, 25.65f, 0.0f, false, false, 2.1f, -3.44f)
            arcTo(24.85f, 24.85f, 0.0f, false, false, 837.98f, 420.97f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(813.35f, 412.98f)
            arcToRelative(21.91f, 21.91f, 0.0f, false, true, 0.17f, -4.41f)
            arcToRelative(24.93f, 24.93f, 0.0f, false, false, -4.78f, 6.57f)
            arcToRelative(25.63f, 25.63f, 0.0f, false, false, -1.52f, 3.73f)
            arcTo(22.75f, 22.75f, 0.0f, false, true, 813.35f, 412.98f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(744.42f, 542.91f)
            arcToRelative(23.59f, 23.59f, 0.0f, false, true, 2.95f, -3.38f)
            arcToRelative(22.92f, 22.92f, 0.0f, false, true, 0.87f, -4.0f)
            arcToRelative(25.09f, 25.09f, 0.0f, false, false, -2.29f, 3.71f)
            arcTo(25.59f, 25.59f, 0.0f, false, false, 744.42f, 542.91f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(751.96f, 528.01f)
            arcToRelative(23.59f, 23.59f, 0.0f, false, true, 2.95f, -3.38f)
            arcToRelative(23.0f, 23.0f, 0.0f, false, true, 0.83f, -3.9f)
            lineToRelative(0.0f, 0.0f)
            arcToRelative(25.68f, 25.68f, 0.0f, false, false, -2.2f, 3.59f)
            arcTo(26.76f, 26.76f, 0.0f, false, false, 751.96f, 528.01f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(816.25f, 463.91f)
            arcToRelative(20.82f, 20.82f, 0.0f, false, true, -3.43f, 2.74f)
            arcToRelative(22.45f, 22.45f, 0.0f, false, true, -0.63f, 6.79f)
            arcToRelative(18.75f, 18.75f, 0.0f, false, true, 2.0f, 0.46f)
            arcToRelative(24.76f, 24.76f, 0.0f, false, true, 4.23f, -1.83f)
            curveToRelative(10.18f, -3.34f, 20.47f, 0.21f, 23.0f, 7.92f)
            arcToRelative(11.68f, 11.68f, 0.0f, false, true, 0.32f, 6.0f)
            curveToRelative(3.35f, -3.94f, 4.74f, -8.74f, 3.3f, -13.14f)
            curveToRelative(-2.54f, -7.71f, -12.84f, -11.26f, -23.0f, -7.92f)
            arcToRelative(24.76f, 24.76f, 0.0f, false, false, -4.23f, 1.83f)
            curveToRelative(-0.65f, -0.18f, -1.32f, -0.34f, -2.0f, -0.46f)
            curveTo(816.0f, 465.5f, 816.14f, 464.71f, 816.25f, 463.91f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(740.7f, 550.4f)
            arcToRelative(25.09f, 25.09f, 0.0f, false, false, -2.29f, 3.71f)
            arcToRelative(26.56f, 26.56f, 0.0f, false, false, -1.54f, 3.74f)
            arcToRelative(24.48f, 24.48f, 0.0f, false, true, 3.0f, -3.4f)
            arcTo(22.92f, 22.92f, 0.0f, false, true, 740.7f, 550.4f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(802.44f, 434.45f)
            arcToRelative(22.0f, 22.0f, 0.0f, false, true, 0.17f, -4.41f)
            arcToRelative(24.93f, 24.93f, 0.0f, false, false, -4.78f, 6.57f)
            arcToRelative(25.63f, 25.63f, 0.0f, false, false, -1.52f, 3.73f)
            arcTo(22.75f, 22.75f, 0.0f, false, true, 802.44f, 434.45f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(786.75f, 522.01f)
            arcToRelative(24.29f, 24.29f, 0.0f, false, true, -1.0f, 4.38f)
            arcToRelative(26.67f, 26.67f, 0.0f, false, false, 2.09f, -3.44f)
            arcToRelative(25.49f, 25.49f, 0.0f, false, false, 1.63f, -4.0f)
            arcTo(24.23f, 24.23f, 0.0f, false, true, 786.75f, 522.01f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(771.67f, 551.83f)
            arcToRelative(24.15f, 24.15f, 0.0f, false, true, -1.0f, 4.38f)
            arcToRelative(25.65f, 25.65f, 0.0f, false, false, 2.1f, -3.44f)
            arcToRelative(26.47f, 26.47f, 0.0f, false, false, 1.63f, -4.0f)
            arcTo(23.57f, 23.57f, 0.0f, false, true, 771.67f, 551.83f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(779.21f, 536.91f)
            arcToRelative(24.11f, 24.11f, 0.0f, false, true, -1.0f, 4.38f)
            arcTo(26.67f, 26.67f, 0.0f, false, false, 780.35f, 537.91f)
            arcToRelative(25.49f, 25.49f, 0.0f, false, false, 1.63f, -4.0f)
            arcTo(24.23f, 24.23f, 0.0f, false, true, 779.21f, 536.91f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(794.29f, 507.1f)
            arcToRelative(24.0f, 24.0f, 0.0f, false, true, -1.0f, 4.37f)
            arcToRelative(25.89f, 25.89f, 0.0f, false, false, 2.09f, -3.43f)
            arcToRelative(25.48f, 25.48f, 0.0f, false, false, 1.64f, -4.07f)
            arcTo(23.22f, 23.22f, 0.0f, false, true, 794.29f, 507.1f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(744.92f, 469.39f)
            curveToRelative(7.72f, -2.53f, 16.68f, 3.66f, 20.0f, 13.83f)
            arcToRelative(25.68f, 25.68f, 0.0f, false, true, 1.0f, 4.51f)
            arcToRelative(21.18f, 21.18f, 0.0f, false, true, 4.44f, 4.73f)
            arcToRelative(25.53f, 25.53f, 0.0f, false, true, 2.08f, -5.75f)
            curveToRelative(0.35f, -0.68f, 0.73f, -1.33f, 1.12f, -2.0f)
            arcToRelative(21.07f, 21.07f, 0.0f, false, false, -4.0f, -4.18f)
            arcToRelative(25.38f, 25.38f, 0.0f, false, false, -1.0f, -4.5f)
            curveToRelative(-3.34f, -10.17f, -12.3f, -16.37f, -20.0f, -13.83f)
            curveToRelative(-4.4f, 1.44f, -7.44f, 5.41f, -8.62f, 10.43f)
            arcTo(11.69f, 11.69f, 0.0f, false, true, 744.92f, 469.39f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(780.72f, 477.39f)
            arcToRelative(21.85f, 21.85f, 0.0f, false, true, 0.18f, -4.4f)
            arcToRelative(24.73f, 24.73f, 0.0f, false, false, -4.79f, 6.57f)
            arcToRelative(25.07f, 25.07f, 0.0f, false, false, -1.52f, 3.72f)
            arcTo(23.0f, 23.0f, 0.0f, false, true, 780.72f, 477.39f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(791.58f, 455.91f)
            arcToRelative(21.85f, 21.85f, 0.0f, false, true, 0.18f, -4.4f)
            arcToRelative(24.52f, 24.52f, 0.0f, false, false, -4.79f, 6.56f)
            arcToRelative(25.63f, 25.63f, 0.0f, false, false, -1.52f, 3.73f)
            arcTo(22.75f, 22.75f, 0.0f, false, true, 791.58f, 455.91f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(764.13f, 566.74f)
            arcToRelative(24.25f, 24.25f, 0.0f, false, true, -1.0f, 4.39f)
            arcToRelative(25.73f, 25.73f, 0.0f, false, false, 3.73f, -7.49f)
            arcTo(23.57f, 23.57f, 0.0f, false, true, 764.13f, 566.74f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(859.87f, 373.62f)
            arcToRelative(23.68f, 23.68f, 0.0f, false, false, 8.23f, -9.32f)
            curveToRelative(4.91f, -9.7f, 2.88f, -20.61f, -4.53f, -24.36f)
            reflectiveCurveToRelative(-17.4f, 1.08f, -22.31f, 10.79f)
            arcTo(23.68f, 23.68f, 0.0f, false, false, 838.64f, 362.91f)
            arcToRelative(24.63f, 24.63f, 0.0f, false, false, -10.86f, 21.47f)
            arcToRelative(23.61f, 23.61f, 0.0f, false, false, -8.23f, 9.32f)
            arcToRelative(24.69f, 24.69f, 0.0f, false, false, -1.51f, 3.69f)
            arcToRelative(20.85f, 20.85f, 0.0f, false, true, 10.77f, -8.0f)
            arcTo(12.24f, 12.24f, 0.0f, false, false, 844.35f, 397.23f)
            arcToRelative(20.85f, 20.85f, 0.0f, false, true, 0.0f, 13.41f)
            arcToRelative(25.48f, 25.48f, 0.0f, false, false, 2.08f, -3.4f)
            arcToRelative(23.68f, 23.68f, 0.0f, false, false, 2.62f, -12.15f)
            arcToRelative(24.57f, 24.57f, 0.0f, false, false, 10.86f, -21.47f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(979.23f, 235.98f)
            arcToRelative(72.0f, 72.0f, 0.0f, false, false, 8.72f, -4.83f)
            lineToRelative(-32.33f, -23.62f)
            lineToRelative(38.0f, 19.57f)
            arcToRelative(72.13f, 72.13f, 0.0f, false, false, 27.0f, -50.31f)
            lineToRelative(-64.58f, 0.66f)
            lineToRelative(64.72f, -10.82f)
            arcTo(72.0f, 72.0f, 0.0f, true, false, 878.18f, 184.91f)
            arcToRelative(72.09f, 72.09f, 0.0f, false, false, -13.26f, 8.0f)
            lineToRelative(33.75f, 46.93f)
            lineTo(858.15f, 198.76f)
            arcToRelative(72.08f, 72.08f, 0.0f, false, false, -20.17f, 65.61f)
            arcToRelative(72.0f, 72.0f, 0.0f, true, false, 101.05f, 51.1f)
            arcToRelative(72.0f, 72.0f, 0.0f, false, false, 40.2f, -79.49f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(844.35f, 217.66f)
            arcToRelative(71.77f, 71.77f, 0.0f, false, false, -6.35f, 46.71f)
            arcToRelative(72.0f, 72.0f, 0.0f, true, false, 101.05f, 51.1f)
            curveTo(953.18f, 308.91f, 847.97f, 210.45f, 844.35f, 217.66f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(925.49f, 102.72f)
            moveToRelative(-10.69f, 0.0f)
            arcToRelative(10.69f, 10.69f, 0.0f, true, true, 21.38f, 0.0f)
            arcToRelative(10.69f, 10.69f, 0.0f, true, true, -21.38f, 0.0f)
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(987.93f, 109.58f)
            moveToRelative(-10.69f, 0.0f)
            arcToRelative(10.69f, 10.69f, 0.0f, true, true, 21.38f, 0.0f)
            arcToRelative(10.69f, 10.69f, 0.0f, true, true, -21.38f, 0.0f)
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(1012.01f, 205.64f)
            moveToRelative(-10.69f, 0.0f)
            arcToRelative(10.69f, 10.69f, 0.0f, true, true, 21.38f, 0.0f)
            arcToRelative(10.69f, 10.69f, 0.0f, true, true, -21.38f, 0.0f)
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(979.51f, 253.61f)
            moveToRelative(-10.69f, 0.0f)
            arcToRelative(10.69f, 10.69f, 0.0f, true, true, 21.38f, 0.0f)
            arcToRelative(10.69f, 10.69f, 0.0f, true, true, -21.38f, 0.0f)
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(935.11f, 353.24f)
            moveToRelative(-10.69f, 0.0f)
            arcToRelative(10.69f, 10.69f, 0.0f, true, true, 21.38f, 0.0f)
            arcToRelative(10.69f, 10.69f, 0.0f, true, true, -21.38f, 0.0f)
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(935.11f, 353.24f)
            moveToRelative(-10.69f, 0.0f)
            arcToRelative(10.69f, 10.69f, 0.0f, true, true, 21.38f, 0.0f)
            arcToRelative(10.69f, 10.69f, 0.0f, true, true, -21.38f, 0.0f)
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(836.39f, 240.4f)
            moveToRelative(-10.69f, 0.0f)
            arcToRelative(10.69f, 10.69f, 0.0f, true, true, 21.38f, 0.0f)
            arcToRelative(10.69f, 10.69f, 0.0f, true, true, -21.38f, 0.0f)
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(836.39f, 240.4f)
            moveToRelative(-10.69f, 0.0f)
            arcToRelative(10.69f, 10.69f, 0.0f, true, true, 21.38f, 0.0f)
            arcToRelative(10.69f, 10.69f, 0.0f, true, true, -21.38f, 0.0f)
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(824.99f, 416.23f)
            curveToRelative(-7.11f, -3.59f, -9.26f, -13.76f, -5.11f, -23.14f)
            curveToRelative(-0.22f, 0.38f, -0.43f, 0.77f, -0.63f, 1.18f)
            curveToRelative(-4.91f, 9.7f, -2.88f, 20.6f, 4.53f, 24.35f)
            reflectiveCurveToRelative(17.4f, -1.08f, 22.3f, -10.78f)
            curveToRelative(0.21f, -0.4f, 0.39f, -0.81f, 0.57f, -1.21f)
            curveTo(841.56f, 415.54f, 832.09f, 419.83f, 824.99f, 416.23f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(814.43f, 437.11f)
            curveToRelative(-7.1f, -3.59f, -9.26f, -13.76f, -5.1f, -23.15f)
            curveToRelative(-0.22f, 0.39f, -0.44f, 0.78f, -0.64f, 1.18f)
            curveToRelative(-4.91f, 9.7f, -2.88f, 20.61f, 4.53f, 24.35f)
            reflectiveCurveToRelative(17.4f, -1.07f, 22.31f, -10.78f)
            curveToRelative(0.2f, -0.4f, 0.39f, -0.8f, 0.57f, -1.21f)
            curveTo(831.0f, 436.41f, 821.54f, 440.7f, 814.43f, 437.11f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(803.87f, 457.98f)
            curveToRelative(-7.1f, -3.59f, -9.26f, -13.76f, -5.1f, -23.14f)
            curveToRelative(-0.22f, 0.38f, -0.44f, 0.77f, -0.64f, 1.18f)
            curveToRelative(-4.91f, 9.7f, -2.87f, 20.6f, 4.54f, 24.35f)
            reflectiveCurveToRelative(17.39f, -1.08f, 22.3f, -10.78f)
            curveToRelative(0.2f, -0.4f, 0.39f, -0.81f, 0.57f, -1.21f)
            curveTo(820.45f, 457.29f, 810.98f, 461.58f, 803.87f, 457.98f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(793.35f, 478.91f)
            curveToRelative(-7.11f, -3.59f, -9.26f, -13.76f, -5.11f, -23.14f)
            curveToRelative(-0.22f, 0.38f, -0.43f, 0.77f, -0.64f, 1.17f)
            curveToRelative(-4.9f, 9.7f, -2.87f, 20.61f, 4.54f, 24.36f)
            reflectiveCurveToRelative(17.4f, -1.08f, 22.3f, -10.79f)
            curveToRelative(0.21f, -0.4f, 0.39f, -0.8f, 0.57f, -1.21f)
            curveTo(809.89f, 478.16f, 800.42f, 482.45f, 793.35f, 478.91f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(782.76f, 499.73f)
            curveToRelative(-7.11f, -3.59f, -9.26f, -13.76f, -5.11f, -23.14f)
            curveToRelative(-0.22f, 0.38f, -0.43f, 0.77f, -0.63f, 1.18f)
            curveToRelative(-4.91f, 9.7f, -2.88f, 20.6f, 4.53f, 24.35f)
            reflectiveCurveToRelative(17.4f, -1.08f, 22.31f, -10.78f)
            curveToRelative(0.2f, -0.4f, 0.39f, -0.81f, 0.57f, -1.21f)
            curveTo(799.35f, 499.04f, 789.86f, 503.33f, 782.76f, 499.73f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(772.2f, 520.61f)
            curveToRelative(-7.1f, -3.59f, -9.26f, -13.76f, -5.1f, -23.14f)
            curveToRelative(-0.22f, 0.38f, -0.44f, 0.77f, -0.64f, 1.17f)
            curveToRelative(-4.91f, 9.7f, -2.88f, 20.61f, 4.54f, 24.36f)
            reflectiveCurveTo(788.35f, 521.91f, 793.35f, 512.21f)
            curveToRelative(0.2f, -0.4f, 0.39f, -0.8f, 0.57f, -1.21f)
            curveTo(788.77f, 519.91f, 779.35f, 524.2f, 772.2f, 520.61f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(761.64f, 541.48f)
            curveToRelative(-7.1f, -3.59f, -9.26f, -13.76f, -5.1f, -23.14f)
            curveToRelative(-0.22f, 0.39f, -0.43f, 0.77f, -0.64f, 1.18f)
            curveToRelative(-4.9f, 9.7f, -2.87f, 20.6f, 4.54f, 24.35f)
            reflectiveCurveToRelative(17.4f, -1.08f, 22.3f, -10.78f)
            curveToRelative(0.21f, -0.4f, 0.39f, -0.81f, 0.57f, -1.21f)
            curveTo(778.22f, 540.79f, 768.75f, 545.08f, 761.64f, 541.48f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(751.09f, 562.36f)
            curveToRelative(-7.11f, -3.59f, -9.26f, -13.76f, -5.11f, -23.14f)
            quadToRelative(-0.33f, 0.57f, -0.63f, 1.17f)
            curveToRelative(-4.91f, 9.7f, -2.88f, 20.61f, 4.53f, 24.36f)
            reflectiveCurveToRelative(17.4f, -1.08f, 22.31f, -10.79f)
            curveToRelative(0.2f, -0.4f, 0.38f, -0.8f, 0.56f, -1.21f)
            curveTo(767.66f, 561.66f, 758.19f, 565.91f, 751.09f, 562.36f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(744.15f, 576.08f)
            curveToRelative(-7.1f, -3.6f, -9.26f, -13.76f, -5.1f, -23.15f)
            curveToRelative(-0.22f, 0.39f, -0.44f, 0.78f, -0.64f, 1.18f)
            curveToRelative(-4.91f, 9.7f, -2.88f, 20.61f, 4.53f, 24.35f)
            reflectiveCurveToRelative(17.4f, -1.07f, 22.31f, -10.78f)
            curveToRelative(0.2f, -0.4f, 0.39f, -0.8f, 0.57f, -1.21f)
            curveTo(760.72f, 575.38f, 751.25f, 579.67f, 744.15f, 576.08f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(934.94f, 483.17f)
            arcToRelative(37.4f, 37.4f, 0.0f, false, false, 2.0f, -9.79f)
            arcToRelative(5.34f, 5.34f, 0.0f, true, false, -0.81f, -10.62f)
            arcToRelative(37.31f, 37.31f, 0.0f, false, false, -19.64f, -25.31f)
            arcToRelative(5.34f, 5.34f, 0.0f, true, false, -9.54f, -4.82f)
            arcToRelative(5.0f, 5.0f, 0.0f, false, false, -0.48f, 1.43f)
            arcTo(37.49f, 37.49f, 0.0f, false, false, 873.6f, 443.91f)
            arcToRelative(37.64f, 37.64f, 0.0f, false, false, -9.82f, -0.29f)
            lineToRelative(-4.33f, 19.71f)
            lineToRelative(-0.26f, -19.0f)
            arcToRelative(37.42f, 37.42f, 0.0f, true, false, 20.0f, 72.0f)
            arcToRelative(5.28f, 5.28f, 0.0f, false, false, 2.25f, 2.16f)
            arcToRelative(5.34f, 5.34f, 0.0f, false, false, 7.32f, -6.88f)
            arcToRelative(37.35f, 37.35f, 0.0f, false, false, 4.8f, -3.92f)
            arcToRelative(37.45f, 37.45f, 0.0f, false, false, 39.31f, -19.83f)
            lineToRelative(-20.15f, -3.39f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(903.81f, 492.74f)
            curveToRelative(-7.44f, -9.82f, -12.0f, -21.45f, -17.25f, -32.61f)
            arcToRelative(136.38f, 136.38f, 0.0f, false, false, -7.07f, -13.36f)
            lineToRelative(-3.94f, -4.59f)
            curveToRelative(-0.66f, 0.56f, -1.32f, 1.14f, -2.0f, 1.75f)
            arcToRelative(37.64f, 37.64f, 0.0f, false, false, -9.82f, -0.29f)
            lineToRelative(-4.33f, 19.71f)
            lineToRelative(-0.26f, -19.0f)
            arcToRelative(37.42f, 37.42f, 0.0f, true, false, 20.0f, 72.0f)
            arcToRelative(5.28f, 5.28f, 0.0f, false, false, 2.25f, 2.16f)
            arcToRelative(5.34f, 5.34f, 0.0f, false, false, 7.32f, -6.88f)
            arcToRelative(37.35f, 37.35f, 0.0f, false, false, 4.8f, -3.92f)
            arcToRelative(37.49f, 37.49f, 0.0f, false, false, 22.44f, -3.32f)
            arcTo(52.71f, 52.71f, 0.0f, false, true, 903.81f, 492.74f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(772.06f, 450.54f)
            arcToRelative(5.34f, 5.34f, 0.0f, false, false, -4.78f, -7.75f)
            arcToRelative(37.37f, 37.37f, 0.0f, false, false, -11.7f, -20.83f)
            arcToRelative(37.52f, 37.52f, 0.0f, false, false, 1.1f, -11.7f)
            lineTo(737.03f, 406.91f)
            lineToRelative(19.08f, -1.2f)
            arcToRelative(38.0f, 38.0f, 0.0f, false, false, -1.41f, -5.28f)
            arcToRelative(5.46f, 5.46f, 0.0f, false, false, 1.76f, -2.0f)
            arcToRelative(5.35f, 5.35f, 0.0f, false, false, -6.59f, -7.44f)
            arcTo(37.42f, 37.42f, 0.0f, false, false, 683.08f, 421.91f)
            arcToRelative(5.25f, 5.25f, 0.0f, false, false, -2.66f, 2.5f)
            arcToRelative(5.34f, 5.34f, 0.0f, false, false, 6.75f, 7.37f)
            arcToRelative(37.53f, 37.53f, 0.0f, false, false, 7.09f, 8.65f)
            arcToRelative(37.4f, 37.4f, 0.0f, false, false, 1.74f, 23.76f)
            lineTo(722.35f, 457.35f)
            lineTo(698.85f, 469.71f)
            arcToRelative(37.42f, 37.42f, 0.0f, false, false, 68.91f, -16.26f)
            arcTo(5.33f, 5.33f, 0.0f, false, false, 772.06f, 450.54f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(772.06f, 450.54f)
            arcToRelative(5.34f, 5.34f, 0.0f, false, false, -4.78f, -7.75f)
            arcToRelative(37.37f, 37.37f, 0.0f, false, false, -11.7f, -20.83f)
            curveToRelative(0.16f, -0.63f, 0.3f, -1.25f, 0.42f, -1.88f)
            arcToRelative(13.18f, 13.18f, 0.0f, false, false, -1.93f, 1.71f)
            curveToRelative(-3.55f, 3.89f, -5.37f, 9.12f, -8.88f, 13.05f)
            curveToRelative(-4.14f, 4.64f, -10.34f, 7.06f, -16.53f, 7.71f)
            reflectiveCurveToRelative(-12.42f, -0.3f, -18.53f, -1.49f)
            curveToRelative(-5.06f, -1.0f, -10.19f, -2.16f, -15.32f, -1.67f)
            curveToRelative(-0.5f, 0.0f, -1.0f, 0.11f, -1.46f, 0.19f)
            curveToRelative(0.3f, 0.29f, 0.6f, 0.58f, 0.91f, 0.86f)
            arcToRelative(37.4f, 37.4f, 0.0f, false, false, 1.74f, 23.76f)
            lineTo(722.35f, 457.35f)
            lineTo(698.85f, 469.71f)
            arcToRelative(37.42f, 37.42f, 0.0f, false, false, 68.91f, -16.26f)
            arcTo(5.33f, 5.33f, 0.0f, false, false, 772.06f, 450.54f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(89.49f, 511.24f)
            curveToRelative(1.28f, -3.89f, 6.47f, -5.68f, 11.59f, -4.0f)
            arcToRelative(12.78f, 12.78f, 0.0f, false, true, 2.13f, 0.92f)
            curveToRelative(0.33f, -0.09f, 0.67f, -0.17f, 1.0f, -0.23f)
            arcToRelative(11.26f, 11.26f, 0.0f, false, true, -0.32f, -3.42f)
            arcToRelative(12.46f, 12.46f, 0.0f, false, true, -5.47f, -10.82f)
            arcToRelative(11.92f, 11.92f, 0.0f, false, true, -4.14f, -4.69f)
            arcToRelative(12.0f, 12.0f, 0.0f, false, true, -1.33f, -6.12f)
            arcToRelative(12.0f, 12.0f, 0.0f, false, true, -4.14f, -4.7f)
            curveTo(86.35f, 473.3f, 87.35f, 467.81f, 91.1f, 465.91f)
            reflectiveCurveToRelative(8.77f, 0.55f, 11.24f, 5.43f)
            arcToRelative(12.0f, 12.0f, 0.0f, false, true, 1.32f, 6.13f)
            arcToRelative(12.37f, 12.37f, 0.0f, false, true, 5.47f, 10.81f)
            arcToRelative(12.4f, 12.4f, 0.0f, false, true, 5.47f, 10.82f)
            arcTo(12.37f, 12.37f, 0.0f, false, true, 120.07f, 509.91f)
            arcToRelative(12.0f, 12.0f, 0.0f, false, true, 4.15f, 4.7f)
            arcToRelative(12.66f, 12.66f, 0.0f, false, true, 1.0f, 2.9f)
            arcToRelative(10.66f, 10.66f, 0.0f, false, true, 2.23f, -2.39f)
            arcToRelative(12.58f, 12.58f, 0.0f, false, true, 0.52f, -2.27f)
            curveToRelative(1.69f, -5.12f, 6.2f, -8.24f, 10.09f, -7.0f)
            reflectiveCurveToRelative(5.67f, 6.47f, 4.0f, 11.59f)
            arcToRelative(11.67f, 11.67f, 0.0f, false, true, -3.81f, 5.59f)
            arcToRelative(12.67f, 12.67f, 0.0f, false, true, -0.52f, 2.26f)
            arcToRelative(11.1f, 11.1f, 0.0f, false, true, -5.11f, 6.42f)
            arcToRelative(12.84f, 12.84f, 0.0f, false, true, 0.42f, 2.0f)
            arcToRelative(12.57f, 12.57f, 0.0f, false, true, 2.53f, 3.43f)
            arcToRelative(12.32f, 12.32f, 0.0f, false, true, 1.26f, 4.08f)
            arcToRelative(12.55f, 12.55f, 0.0f, false, true, 2.54f, 3.43f)
            arcToRelative(12.39f, 12.39f, 0.0f, false, true, 1.26f, 4.08f)
            arcToRelative(12.45f, 12.45f, 0.0f, false, true, 2.54f, 3.44f)
            curveToRelative(2.47f, 4.88f, 1.45f, 10.38f, -2.28f, 12.26f)
            reflectiveCurveToRelative(-8.77f, -0.54f, -11.24f, -5.43f)
            arcToRelative(12.48f, 12.48f, 0.0f, false, true, -1.26f, -4.08f)
            arcToRelative(12.7f, 12.7f, 0.0f, false, true, -3.8f, -7.51f)
            arcToRelative(12.7f, 12.7f, 0.0f, false, true, -3.8f, -7.51f)
            arcToRelative(12.6f, 12.6f, 0.0f, false, true, -2.54f, -3.43f)
            arcToRelative(12.73f, 12.73f, 0.0f, false, true, -1.26f, -4.08f)
            arcToRelative(12.57f, 12.57f, 0.0f, false, true, -2.53f, -3.43f)
            arcToRelative(12.32f, 12.32f, 0.0f, false, true, -1.26f, -4.08f)
            arcToRelative(11.63f, 11.63f, 0.0f, false, true, -1.71f, -2.0f)
            arcToRelative(11.9f, 11.9f, 0.0f, false, true, -6.18f, -0.38f)
            arcToRelative(13.29f, 13.29f, 0.0f, false, true, -2.14f, -0.93f)
            arcToRelative(11.61f, 11.61f, 0.0f, false, true, -6.75f, -0.24f)
            curveTo(91.35f, 519.64f, 88.22f, 515.13f, 89.49f, 511.24f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(98.52f, 495.91f)
            arcToRelative(10.72f, 10.72f, 0.0f, false, false, 1.74f, 1.39f)
            arcToRelative(11.31f, 11.31f, 0.0f, false, false, 0.56f, 4.24f)
            arcToRelative(11.93f, 11.93f, 0.0f, false, true, -1.06f, -1.73f)
            arcTo(12.46f, 12.46f, 0.0f, false, true, 98.52f, 495.91f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(93.05f, 485.11f)
            arcToRelative(11.21f, 11.21f, 0.0f, false, false, 1.74f, 1.38f)
            arcToRelative(11.39f, 11.39f, 0.0f, false, false, 0.56f, 4.25f)
            arcToRelative(12.7f, 12.7f, 0.0f, false, true, -2.3f, -5.63f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(105.48f, 481.08f)
            arcToRelative(11.11f, 11.11f, 0.0f, false, false, -0.08f, -2.22f)
            arcToRelative(12.66f, 12.66f, 0.0f, false, true, 2.41f, 3.31f)
            arcToRelative(13.0f, 13.0f, 0.0f, false, true, 0.76f, 1.88f)
            arcTo(11.42f, 11.42f, 0.0f, false, false, 105.48f, 481.08f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(140.18f, 546.54f)
            arcToRelative(11.65f, 11.65f, 0.0f, false, false, -1.48f, -1.7f)
            arcToRelative(12.4f, 12.4f, 0.0f, false, false, -0.44f, -2.0f)
            arcToRelative(13.23f, 13.23f, 0.0f, false, true, 1.15f, 1.86f)
            arcTo(13.0f, 13.0f, 0.0f, false, true, 140.18f, 546.54f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(136.35f, 539.03f)
            arcToRelative(11.65f, 11.65f, 0.0f, false, false, -1.48f, -1.7f)
            arcToRelative(11.75f, 11.75f, 0.0f, false, false, -0.42f, -2.0f)
            horizontalLineToRelative(0.0f)
            arcToRelative(12.67f, 12.67f, 0.0f, false, true, 1.88f, 3.68f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(104.0f, 506.75f)
            arcToRelative(10.11f, 10.11f, 0.0f, false, false, 1.73f, 1.37f)
            arcToRelative(10.9f, 10.9f, 0.0f, false, false, 0.32f, 3.42f)
            arcToRelative(10.0f, 10.0f, 0.0f, false, false, -1.0f, 0.23f)
            arcToRelative(13.26f, 13.26f, 0.0f, false, false, -2.14f, -0.92f)
            curveToRelative(-5.12f, -1.68f, -10.31f, 0.11f, -11.58f, 4.0f)
            arcToRelative(5.82f, 5.82f, 0.0f, false, false, -0.17f, 3.0f)
            arcToRelative(6.93f, 6.93f, 0.0f, false, true, -1.66f, -6.61f)
            curveToRelative(1.28f, -3.89f, 6.47f, -5.68f, 11.59f, -4.0f)
            arcToRelative(12.78f, 12.78f, 0.0f, false, true, 2.13f, 0.92f)
            curveToRelative(0.33f, -0.09f, 0.67f, -0.17f, 1.0f, -0.23f)
            arcTo(10.32f, 10.32f, 0.0f, false, true, 104.0f, 506.75f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(142.06f, 550.31f)
            arcToRelative(12.79f, 12.79f, 0.0f, false, true, 1.15f, 1.87f)
            arcToRelative(13.0f, 13.0f, 0.0f, false, true, 0.77f, 1.88f)
            arcToRelative(11.74f, 11.74f, 0.0f, false, false, -1.48f, -1.71f)
            arcTo(12.4f, 12.4f, 0.0f, false, false, 142.06f, 550.31f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(110.95f, 491.91f)
            arcToRelative(11.08f, 11.08f, 0.0f, false, false, -0.08f, -2.22f)
            arcToRelative(12.49f, 12.49f, 0.0f, false, true, 2.41f, 3.31f)
            arcToRelative(12.87f, 12.87f, 0.0f, false, true, 0.76f, 1.87f)
            arcTo(11.38f, 11.38f, 0.0f, false, false, 110.95f, 491.91f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(118.86f, 536.01f)
            arcToRelative(11.94f, 11.94f, 0.0f, false, false, 0.49f, 2.2f)
            arcToRelative(12.63f, 12.63f, 0.0f, false, true, -1.88f, -3.77f)
            arcTo(11.72f, 11.72f, 0.0f, false, false, 118.86f, 536.01f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(126.45f, 551.03f)
            arcToRelative(12.66f, 12.66f, 0.0f, false, false, 0.5f, 2.2f)
            arcToRelative(11.93f, 11.93f, 0.0f, false, true, -1.06f, -1.73f)
            arcToRelative(12.88f, 12.88f, 0.0f, false, true, -0.82f, -2.0f)
            arcTo(13.0f, 13.0f, 0.0f, false, false, 126.45f, 551.03f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(122.66f, 543.52f)
            arcToRelative(11.94f, 11.94f, 0.0f, false, false, 0.49f, 2.2f)
            arcToRelative(12.88f, 12.88f, 0.0f, false, true, -1.06f, -1.73f)
            arcToRelative(12.6f, 12.6f, 0.0f, false, true, -0.82f, -2.0f)
            arcTo(11.72f, 11.72f, 0.0f, false, false, 122.66f, 543.52f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(115.06f, 528.49f)
            arcToRelative(11.86f, 11.86f, 0.0f, false, false, 0.49f, 2.21f)
            arcToRelative(12.44f, 12.44f, 0.0f, false, true, -1.0f, -1.73f)
            arcToRelative(12.23f, 12.23f, 0.0f, false, true, -0.83f, -2.0f)
            arcTo(11.13f, 11.13f, 0.0f, false, false, 115.06f, 528.49f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(139.93f, 509.5f)
            curveToRelative(-3.89f, -1.28f, -8.4f, 1.84f, -10.08f, 7.0f)
            arcToRelative(12.58f, 12.58f, 0.0f, false, false, -0.52f, 2.27f)
            arcToRelative(10.63f, 10.63f, 0.0f, false, false, -2.24f, 2.38f)
            arcToRelative(12.83f, 12.83f, 0.0f, false, false, -1.05f, -2.9f)
            curveToRelative(-0.17f, -0.34f, -0.36f, -0.67f, -0.56f, -1.0f)
            arcToRelative(10.63f, 10.63f, 0.0f, false, true, 2.0f, -2.1f)
            arcToRelative(12.58f, 12.58f, 0.0f, false, true, 0.52f, -2.27f)
            curveToRelative(1.69f, -5.12f, 6.2f, -8.24f, 10.09f, -7.0f)
            arcToRelative(6.93f, 6.93f, 0.0f, false, true, 4.34f, 5.26f)
            arcTo(5.93f, 5.93f, 0.0f, false, false, 139.93f, 509.5f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(121.89f, 513.53f)
            arcToRelative(11.11f, 11.11f, 0.0f, false, false, -0.08f, -2.22f)
            arcToRelative(12.49f, 12.49f, 0.0f, false, true, 2.41f, 3.31f)
            arcToRelative(13.0f, 13.0f, 0.0f, false, true, 0.76f, 1.88f)
            arcTo(11.28f, 11.28f, 0.0f, false, false, 121.89f, 513.53f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(116.42f, 502.71f)
            arcToRelative(11.13f, 11.13f, 0.0f, false, false, -0.08f, -2.22f)
            arcToRelative(12.83f, 12.83f, 0.0f, false, true, 2.41f, 3.31f)
            arcToRelative(13.0f, 13.0f, 0.0f, false, true, 0.76f, 1.88f)
            arcTo(11.42f, 11.42f, 0.0f, false, false, 116.42f, 502.71f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(130.25f, 558.54f)
            arcToRelative(11.9f, 11.9f, 0.0f, false, false, 0.5f, 2.21f)
            arcToRelative(12.61f, 12.61f, 0.0f, false, true, -1.06f, -1.74f)
            arcToRelative(12.88f, 12.88f, 0.0f, false, true, -0.82f, -2.0f)
            arcTo(13.0f, 13.0f, 0.0f, false, false, 130.25f, 558.54f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(82.02f, 461.25f)
            arcToRelative(11.92f, 11.92f, 0.0f, false, true, -4.14f, -4.69f)
            curveToRelative(-2.47f, -4.89f, -1.45f, -10.38f, 2.28f, -12.27f)
            reflectiveCurveToRelative(8.77f, 0.54f, 11.24f, 5.43f)
            arcToRelative(11.93f, 11.93f, 0.0f, false, true, 1.32f, 6.12f)
            arcToRelative(12.4f, 12.4f, 0.0f, false, true, 5.47f, 10.82f)
            arcToRelative(12.0f, 12.0f, 0.0f, false, true, 4.15f, 4.69f)
            arcToRelative(13.23f, 13.23f, 0.0f, false, true, 0.76f, 1.86f)
            arcToRelative(10.57f, 10.57f, 0.0f, false, false, -5.43f, -4.0f)
            arcToRelative(6.16f, 6.16f, 0.0f, false, true, -7.83f, 4.0f)
            arcToRelative(10.52f, 10.52f, 0.0f, false, false, 0.0f, 6.75f)
            arcToRelative(13.19f, 13.19f, 0.0f, false, true, -1.0f, -1.71f)
            arcToRelative(12.0f, 12.0f, 0.0f, false, true, -1.33f, -6.12f)
            arcToRelative(12.43f, 12.43f, 0.0f, false, true, -5.47f, -10.82f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(21.9f, 391.91f)
            arcToRelative(36.81f, 36.81f, 0.0f, false, true, -4.39f, -2.43f)
            lineToRelative(16.28f, -11.9f)
            lineToRelative(-19.14f, 9.85f)
            arcToRelative(36.34f, 36.34f, 0.0f, false, true, -13.59f, -25.34f)
            lineToRelative(32.54f, 0.34f)
            lineToRelative(-32.61f, -5.46f)
            arcToRelative(36.29f, 36.29f, 0.0f, true, true, 71.81f, 9.2f)
            arcToRelative(36.73f, 36.73f, 0.0f, false, true, 6.68f, 4.0f)
            lineToRelative(-17.0f, 23.64f)
            lineToRelative(20.41f, -20.68f)
            arcToRelative(36.32f, 36.32f, 0.0f, false, true, 10.16f, 33.05f)
            arcToRelative(36.29f, 36.29f, 0.0f, true, true, -50.9f, 25.74f)
            arcToRelative(36.28f, 36.28f, 0.0f, false, true, -20.25f, -40.0f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(89.85f, 382.69f)
            arcToRelative(36.12f, 36.12f, 0.0f, false, true, 3.2f, 23.53f)
            arcToRelative(36.29f, 36.29f, 0.0f, true, true, -50.9f, 25.74f)
            curveTo(35.02f, 428.65f, 88.02f, 379.06f, 89.85f, 382.69f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(48.97f, 324.78f)
            moveToRelative(-5.39f, 0.0f)
            arcToRelative(5.39f, 5.39f, 0.0f, true, true, 10.78f, 0.0f)
            arcToRelative(5.39f, 5.39f, 0.0f, true, true, -10.78f, 0.0f)
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(17.51f, 328.24f)
            moveToRelative(-5.39f, 0.0f)
            arcToRelative(5.39f, 5.39f, 0.0f, true, true, 10.78f, 0.0f)
            arcToRelative(5.39f, 5.39f, 0.0f, true, true, -10.78f, 0.0f)
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(5.39f, 376.63f)
            moveToRelative(-5.39f, 0.0f)
            arcToRelative(5.39f, 5.39f, 0.0f, true, true, 10.78f, 0.0f)
            arcToRelative(5.39f, 5.39f, 0.0f, true, true, -10.78f, 0.0f)
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(21.76f, 400.8f)
            moveToRelative(-5.39f, 0.0f)
            arcToRelative(5.39f, 5.39f, 0.0f, true, true, 10.78f, 0.0f)
            arcToRelative(5.39f, 5.39f, 0.0f, true, true, -10.78f, 0.0f)
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(44.12f, 450.99f)
            moveToRelative(-5.39f, 0.0f)
            arcToRelative(5.39f, 5.39f, 0.0f, true, true, 10.78f, 0.0f)
            arcToRelative(5.39f, 5.39f, 0.0f, true, true, -10.78f, 0.0f)
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(44.12f, 450.99f)
            moveToRelative(-5.39f, 0.0f)
            arcToRelative(5.39f, 5.39f, 0.0f, true, true, 10.78f, 0.0f)
            arcToRelative(5.39f, 5.39f, 0.0f, true, true, -10.78f, 0.0f)
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(93.85f, 394.14f)
            moveToRelative(-5.39f, 0.0f)
            arcToRelative(5.39f, 5.39f, 0.0f, true, true, 10.78f, 0.0f)
            arcToRelative(5.39f, 5.39f, 0.0f, true, true, -10.78f, 0.0f)
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(93.85f, 394.14f)
            moveToRelative(-5.39f, 0.0f)
            arcToRelative(5.39f, 5.39f, 0.0f, true, true, 10.78f, 0.0f)
            arcToRelative(5.39f, 5.39f, 0.0f, true, true, -10.78f, 0.0f)
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(99.6f, 482.72f)
            curveToRelative(3.58f, -1.81f, 4.66f, -6.93f, 2.57f, -11.66f)
            curveToRelative(0.11f, 0.2f, 0.22f, 0.39f, 0.32f, 0.59f)
            curveToRelative(2.47f, 4.89f, 1.45f, 10.38f, -2.29f, 12.27f)
            reflectiveCurveToRelative(-8.76f, -0.54f, -11.23f, -5.43f)
            curveToRelative(-0.1f, -0.2f, -0.2f, -0.4f, -0.29f, -0.61f)
            curveTo(91.25f, 482.37f, 96.02f, 484.53f, 99.6f, 482.72f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(104.91f, 493.24f)
            curveToRelative(3.58f, -1.81f, 4.67f, -6.94f, 2.58f, -11.66f)
            curveToRelative(0.11f, 0.19f, 0.22f, 0.39f, 0.32f, 0.59f)
            curveToRelative(2.47f, 4.89f, 1.45f, 10.38f, -2.29f, 12.27f)
            reflectiveCurveToRelative(-8.76f, -0.54f, -11.23f, -5.43f)
            curveToRelative(-0.11f, -0.2f, -0.2f, -0.41f, -0.29f, -0.61f)
            curveTo(96.57f, 492.91f, 101.35f, 495.05f, 104.91f, 493.24f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(110.23f, 503.75f)
            curveToRelative(3.58f, -1.81f, 4.67f, -6.93f, 2.57f, -11.66f)
            curveToRelative(0.12f, 0.2f, 0.22f, 0.39f, 0.33f, 0.6f)
            curveToRelative(2.47f, 4.88f, 1.44f, 10.38f, -2.29f, 12.26f)
            reflectiveCurveToRelative(-8.76f, -0.54f, -11.23f, -5.43f)
            curveToRelative(-0.11f, -0.2f, -0.2f, -0.4f, -0.29f, -0.61f)
            curveTo(101.88f, 503.4f, 106.65f, 505.56f, 110.23f, 503.75f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(115.55f, 514.27f)
            curveToRelative(3.58f, -1.81f, 4.67f, -6.93f, 2.57f, -11.66f)
            curveToRelative(0.11f, 0.19f, 0.22f, 0.39f, 0.32f, 0.59f)
            curveToRelative(2.48f, 4.89f, 1.45f, 10.38f, -2.28f, 12.27f)
            reflectiveCurveToRelative(-8.77f, -0.54f, -11.24f, -5.43f)
            curveToRelative(-0.1f, -0.2f, -0.19f, -0.41f, -0.28f, -0.61f)
            curveTo(107.2f, 513.91f, 111.97f, 516.08f, 115.55f, 514.27f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(120.87f, 524.78f)
            curveToRelative(3.58f, -1.81f, 4.66f, -6.93f, 2.57f, -11.65f)
            curveToRelative(0.11f, 0.19f, 0.22f, 0.39f, 0.32f, 0.59f)
            curveToRelative(2.47f, 4.89f, 1.45f, 10.38f, -2.28f, 12.27f)
            reflectiveCurveToRelative(-8.77f, -0.55f, -11.24f, -5.43f)
            curveToRelative(-0.1f, -0.21f, -0.19f, -0.41f, -0.29f, -0.61f)
            curveTo(112.52f, 524.43f, 117.29f, 526.59f, 120.87f, 524.78f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(126.19f, 535.3f)
            curveToRelative(3.58f, -1.81f, 4.66f, -6.93f, 2.57f, -11.66f)
            curveToRelative(0.11f, 0.2f, 0.22f, 0.39f, 0.32f, 0.59f)
            curveToRelative(2.47f, 4.89f, 1.45f, 10.38f, -2.28f, 12.27f)
            reflectiveCurveToRelative(-8.77f, -0.54f, -11.24f, -5.43f)
            curveToRelative(-0.1f, -0.2f, -0.2f, -0.4f, -0.29f, -0.61f)
            curveTo(117.84f, 534.91f, 122.61f, 537.11f, 126.19f, 535.3f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(131.51f, 545.82f)
            curveToRelative(3.57f, -1.81f, 4.66f, -6.94f, 2.57f, -11.66f)
            curveToRelative(0.11f, 0.19f, 0.22f, 0.39f, 0.32f, 0.59f)
            curveToRelative(2.47f, 4.89f, 1.45f, 10.38f, -2.29f, 12.27f)
            reflectiveCurveToRelative(-8.76f, -0.55f, -11.23f, -5.43f)
            curveToRelative(-0.1f, -0.21f, -0.2f, -0.41f, -0.29f, -0.61f)
            curveTo(123.16f, 545.46f, 127.93f, 547.63f, 131.51f, 545.82f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(136.82f, 556.33f)
            curveToRelative(3.58f, -1.81f, 4.67f, -6.93f, 2.57f, -11.66f)
            curveToRelative(0.12f, 0.2f, 0.22f, 0.39f, 0.33f, 0.6f)
            curveToRelative(2.47f, 4.88f, 1.44f, 10.38f, -2.29f, 12.26f)
            reflectiveCurveToRelative(-8.76f, -0.54f, -11.23f, -5.43f)
            curveToRelative(-0.11f, -0.2f, -0.2f, -0.4f, -0.29f, -0.61f)
            curveTo(128.48f, 555.98f, 133.24f, 558.14f, 136.82f, 556.33f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(140.35f, 563.24f)
            curveToRelative(3.58f, -1.81f, 4.66f, -6.93f, 2.57f, -11.66f)
            curveToRelative(0.11f, 0.2f, 0.22f, 0.39f, 0.32f, 0.6f)
            curveToRelative(2.47f, 4.88f, 1.45f, 10.38f, -2.28f, 12.26f)
            reflectiveCurveToRelative(-8.77f, -0.54f, -11.24f, -5.43f)
            curveToRelative(-0.1f, -0.2f, -0.2f, -0.4f, -0.29f, -0.61f)
            curveTo(131.97f, 562.91f, 136.74f, 565.05f, 140.35f, 563.24f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(44.21f, 516.44f)
            arcToRelative(18.57f, 18.57f, 0.0f, false, true, -1.0f, -4.93f)
            arcToRelative(2.69f, 2.69f, 0.0f, true, true, 0.41f, -5.35f)
            arcToRelative(18.82f, 18.82f, 0.0f, false, true, 9.89f, -12.75f)
            arcToRelative(2.69f, 2.69f, 0.0f, true, true, 4.81f, -2.43f)
            arcToRelative(2.44f, 2.44f, 0.0f, false, true, 0.24f, 0.72f)
            arcToRelative(18.92f, 18.92f, 0.0f, false, true, 16.56f, 5.0f)
            arcToRelative(18.68f, 18.68f, 0.0f, false, true, 5.0f, -0.14f)
            lineToRelative(2.18f, 9.92f)
            lineTo(82.35f, 496.91f)
            arcToRelative(18.85f, 18.85f, 0.0f, true, true, -10.08f, 36.29f)
            arcToRelative(2.76f, 2.76f, 0.0f, false, true, -1.13f, 1.09f)
            arcToRelative(2.7f, 2.7f, 0.0f, false, true, -3.7f, -3.47f)
            arcToRelative(18.84f, 18.84f, 0.0f, false, true, -2.41f, -2.0f)
            arcToRelative(18.87f, 18.87f, 0.0f, false, true, -19.8f, -10.0f)
            lineToRelative(10.15f, -1.71f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(59.89f, 521.26f)
            curveToRelative(3.75f, -5.0f, 6.05f, -10.8f, 8.69f, -16.42f)
            arcToRelative(66.7f, 66.7f, 0.0f, false, true, 3.56f, -6.74f)
            lineToRelative(2.0f, -2.31f)
            curveToRelative(0.34f, 0.29f, 0.67f, 0.58f, 1.0f, 0.88f)
            arcToRelative(18.68f, 18.68f, 0.0f, false, true, 5.0f, -0.14f)
            lineToRelative(2.18f, 9.92f)
            lineTo(82.35f, 496.91f)
            arcToRelative(18.85f, 18.85f, 0.0f, true, true, -10.08f, 36.29f)
            arcToRelative(2.76f, 2.76f, 0.0f, false, true, -1.13f, 1.09f)
            arcToRelative(2.7f, 2.7f, 0.0f, false, true, -3.7f, -3.47f)
            arcToRelative(18.84f, 18.84f, 0.0f, false, true, -2.41f, -2.0f)
            arcToRelative(19.0f, 19.0f, 0.0f, false, true, -11.31f, -1.67f)
            arcTo(26.59f, 26.59f, 0.0f, false, false, 59.89f, 521.26f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(126.26f, 500.0f)
            arcToRelative(2.68f, 2.68f, 0.0f, false, true, 1.19f, -3.61f)
            arcToRelative(2.64f, 2.64f, 0.0f, false, true, 1.22f, -0.29f)
            arcToRelative(18.85f, 18.85f, 0.0f, false, true, 5.89f, -10.5f)
            arcToRelative(18.71f, 18.71f, 0.0f, false, true, -0.55f, -5.89f)
            lineToRelative(9.89f, -1.66f)
            lineToRelative(-9.61f, -0.61f)
            arcToRelative(18.65f, 18.65f, 0.0f, false, true, 0.72f, -2.66f)
            arcToRelative(2.64f, 2.64f, 0.0f, false, true, -0.89f, -1.0f)
            arcToRelative(2.7f, 2.7f, 0.0f, false, true, 3.32f, -3.75f)
            arcToRelative(18.63f, 18.63f, 0.0f, false, true, 6.87f, -5.93f)
            arcToRelative(18.84f, 18.84f, 0.0f, false, true, 26.77f, 21.5f)
            arcToRelative(2.69f, 2.69f, 0.0f, false, true, 0.16f, 4.87f)
            arcToRelative(2.7f, 2.7f, 0.0f, false, true, -2.22f, 0.1f)
            arcTo(18.74f, 18.74f, 0.0f, false, true, 165.45f, 494.91f)
            arcToRelative(18.9f, 18.9f, 0.0f, false, true, -0.87f, 12.0f)
            lineTo(151.35f, 503.43f)
            lineToRelative(11.83f, 6.23f)
            arcTo(18.86f, 18.86f, 0.0f, false, true, 130.35f, 508.12f)
            arcToRelative(19.11f, 19.11f, 0.0f, false, true, -1.94f, -6.65f)
            arcTo(2.67f, 2.67f, 0.0f, false, true, 126.26f, 500.0f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(126.26f, 500.0f)
            arcToRelative(2.68f, 2.68f, 0.0f, false, true, 1.19f, -3.61f)
            arcToRelative(2.64f, 2.64f, 0.0f, false, true, 1.22f, -0.29f)
            arcToRelative(18.85f, 18.85f, 0.0f, false, true, 5.89f, -10.5f)
            curveToRelative(-0.08f, -0.31f, -0.15f, -0.63f, -0.21f, -0.94f)
            arcToRelative(7.27f, 7.27f, 0.0f, false, true, 1.0f, 0.86f)
            curveToRelative(1.79f, 2.0f, 2.71f, 4.59f, 4.47f, 6.57f)
            arcToRelative(12.91f, 12.91f, 0.0f, false, false, 8.33f, 3.89f)
            arcToRelative(31.54f, 31.54f, 0.0f, false, false, 9.33f, -0.75f)
            arcToRelative(27.35f, 27.35f, 0.0f, false, true, 7.72f, -0.85f)
            lineToRelative(0.74f, 0.1f)
            lineToRelative(-0.46f, 0.43f)
            arcToRelative(18.9f, 18.9f, 0.0f, false, true, -0.87f, 12.0f)
            lineTo(151.35f, 503.43f)
            lineToRelative(11.83f, 6.23f)
            arcTo(18.86f, 18.86f, 0.0f, false, true, 130.35f, 508.12f)
            arcToRelative(19.11f, 19.11f, 0.0f, false, true, -1.94f, -6.65f)
            arcTo(2.67f, 2.67f, 0.0f, false, true, 126.26f, 500.0f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(346.35f, 51.91f)
            arcToRelative(20.23f, 20.23f, 0.0f, false, false, -11.74f, 1.28f)
            arcToRelative(17.32f, 17.32f, 0.0f, false, true, -14.1f, 0.0f)
            arcToRelative(19.76f, 19.76f, 0.0f, false, false, -16.58f, 0.33f)
            arcToRelative(10.28f, 10.28f, 0.0f, false, true, -4.77f, 1.19f)
            curveToRelative(-6.72f, 0.0f, -12.31f, -6.77f, -13.47f, -15.7f)
            arcToRelative(13.07f, 13.07f, 0.0f, false, false, 3.36f, -3.62f)
            curveToRelative(3.94f, -6.35f, 10.0f, -10.43f, 16.89f, -10.43f)
            reflectiveCurveToRelative(12.89f, 4.0f, 16.83f, 10.31f)
            arcToRelative(13.0f, 13.0f, 0.0f, false, false, 11.16f, 6.14f)
            horizontalLineToRelative(0.18f)
            curveTo(339.41f, 41.38f, 344.04f, 45.67f, 346.35f, 51.91f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(366.77f, 23.91f)
            lineToRelative(-10.86f, 6.89f)
            lineToRelative(6.59f, -12.0f)
            arcToRelative(10.74f, 10.74f, 0.0f, false, false, -6.57f, -2.34f)
            horizontalLineToRelative(-0.17f)
            arcToRelative(12.89f, 12.89f, 0.0f, false, true, -2.25f, -0.17f)
            lineToRelative(-3.69f, 2.34f)
            lineToRelative(1.58f, -2.87f)
            arcTo(13.19f, 13.19f, 0.0f, false, true, 344.96f, 10.91f)
            lineTo(338.35f, 15.04f)
            lineToRelative(4.16f, -7.57f)
            curveToRelative(-3.85f, -4.63f, -9.05f, -7.47f, -14.76f, -7.47f)
            curveToRelative(-6.85f, 0.0f, -13.0f, 4.08f, -16.9f, 10.43f)
            arcToRelative(12.62f, 12.62f, 0.0f, false, true, -11.17f, 6.0f)
            lineTo(299.35f, 16.43f)
            curveToRelative(-7.56f, 0.0f, -13.7f, 8.57f, -13.7f, 19.16f)
            reflectiveCurveToRelative(6.14f, 19.15f, 13.7f, 19.15f)
            arcToRelative(10.28f, 10.28f, 0.0f, false, false, 4.77f, -1.19f)
            arcToRelative(19.72f, 19.72f, 0.0f, false, true, 16.58f, -0.32f)
            arcToRelative(17.37f, 17.37f, 0.0f, false, false, 14.1f, 0.0f)
            arcToRelative(19.75f, 19.75f, 0.0f, false, true, 16.43f, 0.32f)
            arcToRelative(10.35f, 10.35f, 0.0f, false, false, 4.72f, 1.16f)
            curveToRelative(7.57f, 0.0f, 13.7f, -8.57f, 13.7f, -19.15f)
            arcTo(24.36f, 24.36f, 0.0f, false, false, 366.77f, 23.91f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(120.35f, 190.45f)
            arcToRelative(29.0f, 29.0f, 0.0f, false, false, -16.77f, 1.82f)
            arcToRelative(24.71f, 24.71f, 0.0f, false, true, -20.14f, -0.05f)
            arcToRelative(28.22f, 28.22f, 0.0f, false, false, -23.68f, 0.47f)
            arcToRelative(14.75f, 14.75f, 0.0f, false, true, -6.82f, 1.7f)
            curveToRelative(-9.6f, 0.0f, -17.59f, -9.67f, -19.25f, -22.43f)
            arcToRelative(18.41f, 18.41f, 0.0f, false, false, 4.8f, -5.17f)
            curveToRelative(5.63f, -9.07f, 14.35f, -14.9f, 24.14f, -14.9f)
            reflectiveCurveToRelative(18.4f, 5.76f, 24.0f, 14.73f)
            arcToRelative(18.52f, 18.52f, 0.0f, false, false, 16.0f, 8.77f)
            horizontalLineToRelative(0.25f)
            curveTo(110.46f, 175.38f, 117.08f, 181.51f, 120.35f, 190.45f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(149.55f, 150.4f)
            lineToRelative(-15.52f, 9.84f)
            lineToRelative(9.42f, -17.13f)
            arcToRelative(15.34f, 15.34f, 0.0f, false, false, -9.39f, -3.35f)
            horizontalLineToRelative(-0.25f)
            arcToRelative(18.16f, 18.16f, 0.0f, false, true, -3.22f, -0.24f)
            lineTo(125.35f, 142.91f)
            lineToRelative(2.26f, -4.1f)
            arcToRelative(18.7f, 18.7f, 0.0f, false, true, -9.2f, -7.0f)
            lineToRelative(-9.42f, 6.0f)
            lineTo(114.92f, 126.91f)
            curveToRelative(-5.51f, -6.61f, -12.93f, -10.66f, -21.09f, -10.66f)
            curveToRelative(-9.79f, 0.0f, -18.51f, 5.82f, -24.14f, 14.89f)
            arcToRelative(18.05f, 18.05f, 0.0f, false, true, -16.0f, 8.61f)
            horizontalLineToRelative(-0.53f)
            curveToRelative(-10.81f, 0.0f, -19.57f, 12.25f, -19.57f, 27.37f)
            reflectiveCurveToRelative(8.76f, 27.37f, 19.57f, 27.37f)
            arcToRelative(14.61f, 14.61f, 0.0f, false, false, 6.82f, -1.71f)
            arcToRelative(28.22f, 28.22f, 0.0f, false, true, 23.68f, -0.46f)
            arcToRelative(24.71f, 24.71f, 0.0f, false, false, 20.14f, 0.05f)
            arcToRelative(28.25f, 28.25f, 0.0f, false, true, 23.48f, 0.45f)
            arcToRelative(14.65f, 14.65f, 0.0f, false, false, 6.74f, 1.67f)
            curveToRelative(10.81f, 0.0f, 19.57f, -12.26f, 19.57f, -27.37f)
            arcTo(34.76f, 34.76f, 0.0f, false, false, 149.55f, 150.4f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(747.97f, 90.91f)
            arcToRelative(20.24f, 20.24f, 0.0f, false, true, 11.74f, 1.28f)
            arcToRelative(17.29f, 17.29f, 0.0f, false, false, 14.09f, 0.0f)
            arcToRelative(19.76f, 19.76f, 0.0f, false, true, 16.58f, 0.33f)
            arcToRelative(10.31f, 10.31f, 0.0f, false, false, 4.77f, 1.19f)
            curveToRelative(6.72f, 0.0f, 12.32f, -6.77f, 13.48f, -15.7f)
            arcToRelative(12.93f, 12.93f, 0.0f, false, true, -3.36f, -3.62f)
            curveTo(801.35f, 68.02f, 795.22f, 63.91f, 788.35f, 63.91f)
            reflectiveCurveToRelative(-12.88f, 4.0f, -16.82f, 10.31f)
            arcTo(13.0f, 13.0f, 0.0f, false, true, 760.35f, 80.39f)
            horizontalLineToRelative(-0.17f)
            curveTo(754.86f, 80.38f, 750.22f, 84.67f, 747.97f, 90.91f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(727.49f, 62.91f)
            lineTo(738.35f, 69.78f)
            lineToRelative(-6.6f, -12.0f)
            arcToRelative(10.76f, 10.76f, 0.0f, false, true, 6.57f, -2.34f)
            horizontalLineToRelative(0.18f)
            arcToRelative(13.0f, 13.0f, 0.0f, false, false, 2.25f, -0.17f)
            lineToRelative(3.68f, 2.34f)
            lineToRelative(-1.58f, -2.87f)
            arcTo(13.21f, 13.21f, 0.0f, false, false, 749.35f, 49.91f)
            lineToRelative(6.59f, 4.18f)
            lineToRelative(-4.17f, -7.57f)
            curveToRelative(3.86f, -4.63f, 9.0f, -7.47f, 14.77f, -7.47f)
            curveToRelative(6.85f, 0.0f, 13.0f, 4.08f, 16.9f, 10.43f)
            arcToRelative(12.61f, 12.61f, 0.0f, false, false, 11.17f, 6.0f)
            horizontalLineToRelative(0.36f)
            curveToRelative(7.57f, 0.0f, 13.7f, 8.57f, 13.7f, 19.16f)
            reflectiveCurveToRelative(-6.13f, 19.15f, -13.7f, 19.15f)
            arcToRelative(10.34f, 10.34f, 0.0f, false, true, -4.77f, -1.19f)
            arcToRelative(19.72f, 19.72f, 0.0f, false, false, -16.58f, -0.32f)
            arcToRelative(17.35f, 17.35f, 0.0f, false, true, -14.09f, 0.0f)
            arcToRelative(19.77f, 19.77f, 0.0f, false, false, -16.44f, 0.32f)
            arcToRelative(10.32f, 10.32f, 0.0f, false, true, -4.72f, 1.16f)
            curveToRelative(-7.56f, 0.0f, -13.7f, -8.57f, -13.7f, -19.15f)
            arcTo(24.36f, 24.36f, 0.0f, false, true, 727.49f, 62.91f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(500.63f, 756.88f)
            arcToRelative(115.0f, 20.0f, 0.0f, true, false, 230.0f, 0.0f)
            arcToRelative(115.0f, 20.0f, 0.0f, true, false, -230.0f, 0.0f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFd0d2d5)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(555.44f, 661.88f)
            lineToRelative(-178.19f, -2.29f)
            lineToRelative(0.53f, -4.57f)
            lineToRelative(8.61f, -75.39f)
            lineToRelative(156.49f, 0.0f)
            lineToRelative(11.51f, 75.39f)
            lineToRelative(0.88f, 5.72f)
            lineToRelative(0.17f, 1.14f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(555.27f, 660.74f)
            lineToRelative(-88.92f, 0.0f)
            lineToRelative(-89.1f, -1.15f)
            lineToRelative(0.53f, -4.57f)
            lineToRelative(176.61f, 0.0f)
            lineToRelative(0.88f, 5.72f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFd0d2d5)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(347.55f, 656.17f)
            horizontalLineToRelative(236.45f)
            verticalLineToRelative(5.71f)
            horizontalLineToRelative(-236.45f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(809.59f, 126.83f)
            arcTo(14.87f, 14.87f, 0.0f, false, false, 794.79f, 111.91f)
            lineTo(134.47f, 111.91f)
            arcToRelative(14.87f, 14.87f, 0.0f, false, false, -14.8f, 14.94f)
            lineTo(119.67f, 527.1f)
            lineTo(809.59f, 527.1f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFd0d2d5)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(119.67f, 523.1f)
            verticalLineToRelative(46.88f)
            arcToRelative(14.8f, 14.8f, 0.0f, false, false, 14.8f, 14.8f)
            lineTo(794.79f, 584.78f)
            arcToRelative(14.8f, 14.8f, 0.0f, false, false, 14.8f, -14.8f)
            lineTo(809.59f, 523.1f)
            close()
        }
        path(
            fill = SolidColor(backgroundColor), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(148.23f, 137.01f)
            horizontalLineToRelative(636.23f)
            verticalLineToRelative(359.81f)
            horizontalLineToRelative(-636.23f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(468.06f, 566.99f)
            arcToRelative(15.4f, 15.4f, 0.0f, false, false, 12.13f, -5.89f)
            horizontalLineToRelative(0.0f)
            arcToRelative(16.06f, 16.06f, 0.0f, false, false, 1.2f, -1.76f)
            lineTo(472.92f, 557.91f)
            lineToRelative(9.15f, 0.06f)
            arcToRelative(15.42f, 15.42f, 0.0f, false, false, 0.29f, -12.21f)
            lineToRelative(-12.27f, 6.36f)
            lineToRelative(11.32f, -8.32f)
            arcToRelative(15.42f, 15.42f, 0.0f, true, false, -25.47f, 17.26f)
            horizontalLineToRelative(0.0f)
            arcTo(15.4f, 15.4f, 0.0f, false, false, 468.06f, 566.99f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(483.48f, 584.77f)
            lineToRelative(71.09f, 71.4f)
            lineToRelative(-10.91f, -71.4f)
            lineToRelative(-60.18f, 0.0f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF444053)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(147.73f, 137.05f)
            horizontalLineToRelative(636.92f)
            verticalLineToRelative(21.02f)
            horizontalLineToRelative(-636.92f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, fillAlpha = 0.3f, strokeAlpha
            = 0.3f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(369.45f, 140.71f)
            lineTo(560.19f, 140.71f)
            arcTo(0.58f, 0.58f, 0.0f, false, true, 560.77f, 141.29f)
            lineTo(560.77f, 152.92f)
            arcTo(0.58f, 0.58f, 0.0f, false, true, 560.19f, 153.5f)
            lineTo(369.45f, 153.5f)
            arcTo(0.58f, 0.58f, 0.0f, false, true, 368.87f, 152.92f)
            lineTo(368.87f, 141.29f)
            arcTo(0.58f, 0.58f, 0.0f, false, true, 369.45f, 140.71f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(555.07f, 148.43f)
            horizontalLineToRelative(-0.47f)
            lineToRelative(-0.17f, -0.15f)
            arcToRelative(3.89f, 3.89f, 0.0f, false, false, 0.9f, -2.49f)
            arcToRelative(3.78f, 3.78f, 0.0f, true, false, -3.77f, 3.81f)
            arcToRelative(3.9f, 3.9f, 0.0f, false, false, 2.49f, -0.91f)
            lineToRelative(0.17f, 0.15f)
            verticalLineToRelative(0.47f)
            lineToRelative(2.93f, 2.93f)
            lineToRelative(0.87f, -0.88f)
            close()
            moveTo(551.56f, 148.43f)
            arcToRelative(2.64f, 2.64f, 0.0f, true, true, 2.63f, -2.64f)
            arcTo(2.62f, 2.62f, 0.0f, false, true, 551.56f, 148.43f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFfa5959)), stroke = null, fillAlpha = 0.8f, strokeAlpha
            = 0.8f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(159.18f, 147.56f)
            moveToRelative(-3.85f, 0.0f)
            arcToRelative(3.85f, 3.85f, 0.0f, true, true, 7.7f, 0.0f)
            arcToRelative(3.85f, 3.85f, 0.0f, true, true, -7.7f, 0.0f)
        }
        path(
            fill = SolidColor(Color(0xFFfed253)), stroke = null, fillAlpha = 0.8f, strokeAlpha
            = 0.8f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(169.77f, 147.56f)
            moveToRelative(-3.85f, 0.0f)
            arcToRelative(3.85f, 3.85f, 0.0f, true, true, 7.7f, 0.0f)
            arcToRelative(3.85f, 3.85f, 0.0f, true, true, -7.7f, 0.0f)
        }
        path(
            fill = SolidColor(Color(0xFF8ccf4d)), stroke = null, fillAlpha = 0.8f, strokeAlpha
            = 0.8f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(180.37f, 147.56f)
            moveToRelative(-3.85f, 0.0f)
            arcToRelative(3.85f, 3.85f, 0.0f, true, true, 7.7f, 0.0f)
            arcToRelative(3.85f, 3.85f, 0.0f, true, true, -7.7f, 0.0f)
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(500.05f, 291.4f)
            curveToRelative(-8.73f, -11.6f, -21.38f, -18.92f, -35.46f, -18.92f)
            reflectiveCurveToRelative(-26.73f, 7.32f, -35.46f, 18.92f)
            arcToRelative(38.39f, 38.39f, 0.0f, false, false, 8.4f, 12.65f)
            arcToRelative(38.28f, 38.28f, 0.0f, false, false, 62.52f, -12.65f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(424.52f, 298.19f)
            arcToRelative(9.17f, 9.17f, 0.0f, false, true, -1.76f, -1.0f)
            arcToRelative(7.06f, 7.06f, 0.0f, false, true, -1.94f, -2.51f)
            arcToRelative(7.18f, 7.18f, 0.0f, true, false, -11.72f, 1.74f)
            arcToRelative(8.0f, 8.0f, 0.0f, false, false, 1.7f, 1.37f)
            arcToRelative(4.94f, 4.94f, 0.0f, false, true, 1.91f, 2.0f)
            arcToRelative(14.44f, 14.44f, 0.0f, false, false, 3.32f, 4.36f)
            arcToRelative(29.0f, 29.0f, 0.0f, false, false, 4.55f, 3.2f)
            arcTo(66.31f, 66.31f, 0.0f, false, false, 416.84f, 326.91f)
            arcToRelative(27.05f, 27.05f, 0.0f, false, false, -7.62f, 1.23f)
            arcToRelative(12.29f, 12.29f, 0.0f, false, false, -3.53f, 1.76f)
            arcToRelative(8.48f, 8.48f, 0.0f, false, false, -0.87f, -0.06f)
            arcToRelative(7.19f, 7.19f, 0.0f, true, false, 6.73f, 9.72f)
            arcToRelative(4.59f, 4.59f, 0.0f, false, true, 3.71f, -3.0f)
            arcToRelative(16.75f, 16.75f, 0.0f, false, true, 1.85f, -0.12f)
            arcToRelative(64.4f, 64.4f, 0.0f, false, false, 8.55f, 26.67f)
            curveToRelative(-4.24f, 3.29f, -6.85f, 6.88f, -7.95f, 10.86f)
            arcToRelative(3.0f, 3.0f, 0.0f, false, false, -0.57f, 0.41f)
            arcToRelative(6.81f, 6.81f, 0.0f, false, false, -0.84f, 0.78f)
            arcTo(7.18f, 7.18f, 0.0f, true, false, 428.35f, 377.51f)
            arcToRelative(4.71f, 4.71f, 0.0f, false, true, 1.17f, -5.08f)
            curveToRelative(0.48f, -0.45f, 1.0f, -0.93f, 1.7f, -1.46f)
            curveToRelative(8.11f, 9.47f, 17.76f, 15.54f, 29.81f, 16.26f)
            lineTo(461.03f, 322.98f)
            curveTo(445.1f, 322.11f, 431.22f, 311.74f, 424.52f, 298.19f)
            close()
            moveTo(524.35f, 329.91f)
            arcToRelative(7.93f, 7.93f, 0.0f, false, false, -0.87f, 0.06f)
            arcToRelative(13.56f, 13.56f, 0.0f, false, false, -3.53f, -1.76f)
            arcToRelative(26.86f, 26.86f, 0.0f, false, false, -7.62f, -1.23f)
            arcToRelative(67.76f, 67.76f, 0.0f, false, false, -3.74f, -19.55f)
            arcToRelative(29.0f, 29.0f, 0.0f, false, false, 4.55f, -3.2f)
            arcToRelative(15.58f, 15.58f, 0.0f, false, false, 3.44f, -4.57f)
            arcTo(4.22f, 4.22f, 0.0f, false, true, 518.35f, 297.91f)
            arcToRelative(0.0f, 0.0f, 0.0f, false, false, 0.0f, 0.0f)
            arcToRelative(7.18f, 7.18f, 0.0f, true, false, -10.0f, -3.17f)
            arcToRelative(6.82f, 6.82f, 0.0f, false, true, -1.94f, 2.51f)
            arcToRelative(18.85f, 18.85f, 0.0f, false, true, -1.76f, 1.29f)
            curveToRelative(-6.7f, 13.54f, -20.57f, 23.62f, -36.48f, 24.49f)
            verticalLineToRelative(64.31f)
            curveToRelative(12.0f, -0.72f, 21.71f, -6.82f, 29.81f, -16.26f)
            curveToRelative(0.63f, 0.5f, 1.16f, 0.95f, 1.61f, 1.37f)
            arcToRelative(4.86f, 4.86f, 0.0f, false, true, 1.26f, 5.23f)
            arcToRelative(7.18f, 7.18f, 0.0f, true, false, 12.08f, -2.33f)
            arcToRelative(6.81f, 6.81f, 0.0f, false, false, -0.84f, -0.78f)
            curveToRelative(-0.33f, -0.26f, -0.57f, -0.41f, -0.57f, -0.41f)
            curveToRelative(-1.1f, -4.0f, -3.7f, -7.57f, -8.0f, -10.86f)
            arcToRelative(64.4f, 64.4f, 0.0f, false, false, 8.55f, -26.67f)
            arcToRelative(15.9f, 15.9f, 0.0f, false, true, 1.77f, 0.12f)
            arcToRelative(4.8f, 4.8f, 0.0f, false, true, 3.85f, 3.0f)
            arcToRelative(7.18f, 7.18f, 0.0f, false, false, 13.85f, -2.18f)
            arcTo(7.29f, 7.29f, 0.0f, false, false, 524.35f, 329.91f)
            close()
        }
        path(
            fill = linearGradient(
                0.0f to Color(0x3F808080), 0.54f to Color(0x1E808080), 1.0f
                        to Color(0x19808080), start = Offset(601.8821f, 766.34656f), end =
                Offset(588.64594f, 438.2234f)
            ), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(681.0f, 745.22f)
            arcToRelative(9.56f, 9.56f, 0.0f, false, false, -2.17f, -2.41f)
            curveToRelative(-4.37f, -4.13f, -6.4f, -10.19f, -7.48f, -16.13f)
            reflectiveCurveToRelative(-1.39f, -12.0f, -3.12f, -17.81f)
            arcToRelative(58.74f, 58.74f, 0.0f, false, false, -3.52f, -8.67f)
            curveToRelative(-1.0f, -2.14f, -2.26f, -6.33f, -4.09f, -7.83f)
            arcToRelative(2.7f, 2.7f, 0.0f, false, false, -1.26f, -0.61f)
            arcToRelative(14.85f, 14.85f, 0.0f, false, false, -3.62f, -4.82f)
            curveToRelative(-2.86f, -2.59f, -6.35f, -4.65f, -8.26f, -8.0f)
            arcToRelative(18.0f, 18.0f, 0.0f, false, false, -1.93f, -3.29f)
            curveToRelative(-1.29f, -1.42f, -3.33f, -2.0f, -4.52f, -3.5f)
            curveToRelative(-1.55f, -1.94f, -1.2f, -4.72f, -1.0f, -7.19f)
            reflectiveCurveToRelative(0.0f, -5.43f, -2.14f, -6.73f)
            curveToRelative(-1.28f, -0.79f, -2.93f, -0.71f, -4.26f, -1.41f)
            curveToRelative(-2.0f, -1.0f, -2.79f, -3.52f, -2.75f, -5.77f)
            reflectiveCurveToRelative(0.74f, -4.44f, 0.92f, -6.69f)
            curveToRelative(0.56f, -7.16f, -4.16f, -14.28f, -2.34f, -21.22f)
            curveToRelative(0.69f, -2.63f, 2.27f, -4.91f, 3.44f, -7.36f)
            arcToRelative(15.37f, 15.37f, 0.0f, false, false, 1.0f, -2.54f)
            arcToRelative(35.73f, 35.73f, 0.0f, false, false, 9.74f, 2.35f)
            curveToRelative(-5.51f, -12.36f, -4.11f, -26.72f, -6.68f, -40.0f)
            lineToRelative(0.33f, -0.45f)
            arcToRelative(20.84f, 20.84f, 0.0f, false, false, 4.22f, -11.86f)
            curveToRelative(0.0f, -4.94f, -2.31f, -9.56f, -3.35f, -14.4f)
            curveToRelative(-0.89f, -4.11f, -0.85f, -8.41f, -2.0f, -12.45f)
            arcToRelative(37.65f, 37.65f, 0.0f, false, false, -3.16f, -7.22f)
            quadToRelative(-1.87f, -3.47f, -3.93f, -6.83f)
            quadToRelative(-1.92f, -6.36f, -3.85f, -12.72f)
            curveToRelative(-1.8f, -5.95f, -3.65f, -12.0f, -7.15f, -17.14f)
            curveToRelative(-1.28f, -1.88f, -1.67f, -5.7f, -3.64f, -6.84f)
            arcToRelative(8.81f, 8.81f, 0.0f, false, false, -5.2f, -0.91f)
            curveToRelative(-2.78f, -1.56f, -5.22f, -3.81f, -5.57f, -6.89f)
            arcToRelative(6.68f, 6.68f, 0.0f, false, true, 0.2f, -2.0f)
            lineToRelative(0.34f, 0.0f)
            curveToRelative(4.4f, -0.4f, 6.63f, -4.28f, 8.92f, -7.68f)
            curveToRelative(3.7f, -5.5f, 2.29f, -12.94f, 0.0f, -19.2f)
            arcToRelative(13.0f, 13.0f, 0.0f, false, false, -3.0f, -5.29f)
            curveToRelative(-3.21f, -3.0f, -8.29f, -2.34f, -12.43f, -3.79f)
            curveToRelative(-1.39f, -0.48f, -2.69f, -1.21f, -4.11f, -1.55f)
            curveToRelative(-2.75f, -0.64f, -5.63f, 0.26f, -8.15f, 1.53f)
            reflectiveCurveToRelative(-4.87f, 2.91f, -7.51f, 3.91f)
            reflectiveCurveToRelative(-5.9f, 1.79f, -6.55f, 4.5f)
            arcToRelative(5.0f, 5.0f, 0.0f, false, false, 0.22f, 2.73f)
            arcToRelative(10.18f, 10.18f, 0.0f, false, false, 3.09f, 4.46f)
            arcToRelative(17.81f, 17.81f, 0.0f, false, false, 10.87f, 24.82f)
            curveToRelative(0.76f, 1.22f, 1.47f, 2.46f, 2.12f, 3.73f)
            curveToRelative(1.16f, 2.24f, 2.14f, 4.88f, 1.74f, 7.28f)
            arcToRelative(22.45f, 22.45f, 0.0f, false, false, -2.0f, 2.45f)
            curveToRelative(-2.87f, 4.0f, -4.5f, 8.69f, -6.0f, 13.37f)
            arcToRelative(141.65f, 141.65f, 0.0f, false, false, -4.83f, 18.27f)
            curveToRelative(-2.44f, 5.19f, -5.42f, 10.15f, -7.19f, 15.6f)
            arcToRelative(35.21f, 35.21f, 0.0f, false, true, -9.38f, -1.26f)
            lineToRelative(-8.44f, -1.91f)
            curveToRelative(-2.94f, -0.67f, -6.0f, -1.39f, -8.3f, -3.33f)
            curveToRelative(-1.22f, -1.0f, -2.17f, -2.33f, -3.41f, -3.32f)
            reflectiveCurveToRelative(-3.73f, -2.15f, -5.09f, -1.33f)
            arcToRelative(4.0f, 4.0f, 0.0f, false, false, -0.69f, 0.53f)
            curveToRelative(-1.24f, -2.51f, -2.42f, -5.0f, -3.86f, -7.43f)
            curveToRelative(-2.07f, -3.4f, -5.15f, -6.51f, -9.05f, -7.16f)
            arcToRelative(9.21f, 9.21f, 0.0f, false, false, -6.79f, 1.58f)
            curveToRelative(-2.28f, 1.62f, -3.71f, 5.0f, -2.0f, 7.28f)
            arcToRelative(13.29f, 13.29f, 0.0f, false, false, 1.39f, 1.42f)
            arcToRelative(29.2f, 29.2f, 0.0f, false, true, 3.09f, 4.29f)
            arcTo(21.91f, 21.91f, 0.0f, false, false, 530.35f, 539.14f)
            lineToRelative(-0.35f, 1.0f)
            curveToRelative(-0.71f, 2.08f, -2.22f, 4.59f, -1.84f, 6.75f)
            lineToRelative(37.54f, 12.85f)
            arcToRelative(23.13f, 23.13f, 0.0f, false, false, 7.56f, 1.67f)
            arcToRelative(12.21f, 12.21f, 0.0f, false, false, 3.0f, -0.4f)
            lineToRelative(0.0f, 1.92f)
            verticalLineToRelative(0.08f)
            curveToRelative(0.1f, 13.19f, -2.0f, 26.29f, -2.39f, 39.47f)
            curveToRelative(-0.07f, 2.41f, 0.0f, 5.0f, 1.52f, 6.88f)
            curveToRelative(1.7f, 2.06f, 4.72f, 2.47f, 7.35f, 2.0f)
            quadToRelative(0.95f, -0.16f, 1.86f, -0.42f)
            arcToRelative(69.33f, 69.33f, 0.0f, false, true, 1.92f, 10.11f)
            lineTo(589.0f, 638.91f)
            arcToRelative(27.14f, 27.14f, 0.0f, false, true, 0.39f, 5.54f)
            curveToRelative(-0.29f, 4.19f, -2.5f, 7.94f, -4.1f, 11.81f)
            reflectiveCurveToRelative(-2.55f, 8.5f, -0.45f, 12.12f)
            curveToRelative(0.66f, 1.12f, 1.57f, 2.07f, 2.16f, 3.23f)
            arcToRelative(13.14f, 13.14f, 0.0f, false, true, 1.0f, 5.68f)
            quadToRelative(0.23f, 8.08f, 0.44f, 16.18f)
            arcToRelative(1.83f, 1.83f, 0.0f, false, false, -1.26f, 0.25f)
            curveToRelative(-2.08f, 1.62f, -1.85f, 9.33f, -2.0f, 11.72f)
            curveToRelative(-0.16f, 4.0f, 0.0f, 8.08f, -0.22f, 12.12f)
            arcTo(72.22f, 72.22f, 0.0f, false, true, 582.35f, 733.65f)
            arcToRelative(10.29f, 10.29f, 0.0f, false, true, -2.29f, 4.61f)
            arcToRelative(9.74f, 9.74f, 0.0f, false, true, -4.07f, 2.11f)
            arcToRelative(46.49f, 46.49f, 0.0f, false, true, -15.41f, 2.1f)
            curveToRelative(-2.11f, -0.07f, -4.31f, -0.27f, -6.27f, 0.53f)
            reflectiveCurveToRelative(-3.54f, 3.0f, -2.83f, 5.0f)
            curveToRelative(0.61f, 1.68f, 2.46f, 2.48f, 4.13f, 3.11f)
            arcToRelative(65.53f, 65.53f, 0.0f, false, true, 11.0f, 5.06f)
            arcToRelative(29.74f, 29.74f, 0.0f, false, false, 4.25f, 2.41f)
            arcToRelative(21.91f, 21.91f, 0.0f, false, false, 4.38f, 1.08f)
            lineToRelative(17.73f, 3.06f)
            curveToRelative(3.63f, 0.62f, 8.0f, 1.0f, 10.31f, -2.0f)
            curveToRelative(1.25f, -1.62f, 1.43f, -3.82f, 1.37f, -5.88f)
            curveToRelative(-0.1f, -4.0f, -0.9f, -8.07f, -0.38f, -12.07f)
            arcToRelative(37.14f, 37.14f, 0.0f, false, true, 1.86f, -6.93f)
            quadToRelative(2.65f, -7.87f, 5.32f, -15.73f)
            curveToRelative(1.78f, -5.26f, 3.58f, -10.57f, 4.27f, -16.09f)
            curveToRelative(0.25f, -2.0f, 0.23f, -4.28f, -1.3f, -5.53f)
            arcToRelative(3.54f, 3.54f, 0.0f, false, false, -1.32f, -0.68f)
            curveToRelative(-0.06f, -4.82f, -0.69f, -9.65f, -0.42f, -14.47f)
            curveToRelative(0.07f, -1.2f, 0.19f, -2.4f, 0.29f, -3.61f)
            arcToRelative(17.61f, 17.61f, 0.0f, false, true, 2.47f, 2.92f)
            curveToRelative(1.71f, 2.68f, 2.34f, 6.22f, 4.95f, 8.0f)
            curveToRelative(2.22f, 1.52f, 5.51f, 1.44f, 7.07f, 3.64f)
            arcToRelative(10.26f, 10.26f, 0.0f, false, true, 1.12f, 2.71f)
            arcToRelative(19.32f, 19.32f, 0.0f, false, false, 4.0f, 6.35f)
            arcToRelative(32.0f, 32.0f, 0.0f, false, false, 2.47f, 6.34f)
            quadToRelative(2.21f, 4.67f, 4.4f, 9.31f)
            arcToRelative(108.59f, 108.59f, 0.0f, false, false, 5.0f, 9.77f)
            arcToRelative(27.55f, 27.55f, 0.0f, false, true, 3.35f, 6.46f)
            curveToRelative(1.41f, 4.94f, -0.89f, 10.36f, -4.53f, 14.0f)
            reflectiveCurveToRelative(-8.44f, 5.73f, -13.17f, 7.68f)
            arcToRelative(3.71f, 3.71f, 0.0f, false, false, -1.34f, 0.8f)
            arcToRelative(2.59f, 2.59f, 0.0f, false, false, -0.61f, 1.74f)
            curveToRelative(0.0f, 2.31f, 1.9f, 4.29f, 4.08f, 5.0f)
            arcToRelative(15.26f, 15.26f, 0.0f, false, false, 6.83f, 0.25f)
            curveToRelative(12.78f, -1.55f, 25.52f, -4.67f, 36.82f, -10.88f)
            arcToRelative(13.0f, 13.0f, 0.0f, false, false, 4.39f, -3.39f)
            arcTo(5.13f, 5.13f, 0.0f, false, false, 681.0f, 745.22f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF2f2e41)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(626.99f, 520.97f)
            quadToRelative(3.2f, 4.91f, 6.0f, 10.06f)
            arcToRelative(37.0f, 37.0f, 0.0f, false, true, 3.17f, 7.17f)
            curveToRelative(1.16f, 4.0f, 1.13f, 8.27f, 2.0f, 12.35f)
            curveToRelative(1.0f, 4.8f, 3.34f, 9.38f, 3.34f, 14.3f)
            arcToRelative(20.56f, 20.56f, 0.0f, false, true, -4.22f, 11.76f)
            arcToRelative(55.82f, 55.82f, 0.0f, false, true, -8.82f, 9.1f)
            arcToRelative(187.62f, 187.62f, 0.0f, false, true, -12.48f, -61.21f)
            curveToRelative(-0.1f, -2.9f, -0.12f, -5.89f, 0.94f, -8.6f)
            curveToRelative(0.65f, -1.7f, 3.77f, -6.36f, 5.78f, -4.53f)
            curveToRelative(0.73f, 0.67f, 0.92f, 3.69f, 1.37f, 4.7f)
            arcTo(36.13f, 36.13f, 0.0f, false, false, 626.99f, 520.97f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(626.99f, 520.97f)
            quadToRelative(3.2f, 4.91f, 6.0f, 10.06f)
            arcToRelative(37.0f, 37.0f, 0.0f, false, true, 3.17f, 7.17f)
            curveToRelative(1.16f, 4.0f, 1.13f, 8.27f, 2.0f, 12.35f)
            curveToRelative(1.0f, 4.8f, 3.34f, 9.38f, 3.34f, 14.3f)
            arcToRelative(20.56f, 20.56f, 0.0f, false, true, -4.22f, 11.76f)
            arcToRelative(55.82f, 55.82f, 0.0f, false, true, -8.82f, 9.1f)
            arcToRelative(187.62f, 187.62f, 0.0f, false, true, -12.48f, -61.21f)
            curveToRelative(-0.1f, -2.9f, -0.12f, -5.89f, 0.94f, -8.6f)
            curveToRelative(0.65f, -1.7f, 3.77f, -6.36f, 5.78f, -4.53f)
            curveToRelative(0.73f, 0.67f, 0.92f, 3.69f, 1.37f, 4.7f)
            arcTo(36.13f, 36.13f, 0.0f, false, false, 626.99f, 520.97f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFd0d2d5)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(363.35f, 384.54f)
            curveToRelative(-7.91f, 7.9f, -8.86f, 11.38f, -13.61f, 12.95f)
            reflectiveCurveTo(327.9f, 407.91f, 333.27f, 417.72f)
            lineToRelative(14.44f, 9.54f)
            lineToRelative(0.42f, 0.28f)
            lineToRelative(2.21f, 6.64f)
            lineToRelative(-21.84f, 38.88f)
            reflectiveCurveToRelative(-36.0f, -16.46f, -39.53f, 7.89f)
            reflectiveCurveToRelative(-1.29f, 31.0f, -2.24f, 44.0f)
            reflectiveCurveToRelative(0.31f, 16.76f, -2.86f, 16.76f)
            reflectiveCurveToRelative(-4.42f, -8.54f, -4.42f, -8.54f)
            reflectiveCurveToRelative(-0.2f, -0.39f, -0.55f, -1.12f)
            curveToRelative(-4.71f, -9.79f, -36.68f, -81.66f, 14.53f, -131.06f)
            curveToRelative(0.0f, 0.0f, 23.72f, -1.56f, 29.43f, -28.76f)
            curveToRelative(3.94f, -18.8f, 17.72f, -17.18f, 26.88f, -13.35f)
            arcToRelative(30.38f, 30.38f, 0.0f, false, true, 8.22f, 4.83f)
            arcToRelative(40.48f, 40.48f, 0.0f, false, true, 5.89f, 7.0f)
            curveTo(366.46f, 374.83f, 367.88f, 380.0f, 363.35f, 384.54f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(348.13f, 427.54f)
            lineToRelative(2.21f, 6.64f)
            lineToRelative(-21.84f, 38.88f)
            reflectiveCurveToRelative(-36.0f, -16.46f, -39.53f, 7.89f)
            reflectiveCurveToRelative(-1.29f, 31.0f, -2.24f, 44.0f)
            reflectiveCurveToRelative(0.31f, 16.76f, -2.86f, 16.76f)
            reflectiveCurveToRelative(-4.42f, -8.54f, -4.42f, -8.54f)
            reflectiveCurveToRelative(-0.2f, -0.39f, -0.55f, -1.12f)
            curveToRelative(-1.59f, -12.16f, -9.65f, -83.29f, 19.57f, -79.51f)
            curveToRelative(0.0f, 0.0f, 23.71f, 11.08f, 27.5f, 6.66f)
            curveToRelative(3.0f, -3.5f, 16.32f, -23.63f, 21.74f, -31.89f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(363.85f, 370.65f)
            lineToRelative(-14.44f, 16.41f)
            reflectiveCurveToRelative(-16.77f, 22.44f, -20.25f, 21.49f)
            arcToRelative(16.23f, 16.23f, 0.0f, false, false, -3.17f, -0.37f)
            arcToRelative(6.16f, 6.16f, 0.0f, false, true, -4.0f, -10.38f)
            curveToRelative(5.72f, -6.0f, 16.26f, -17.39f, 16.95f, -19.92f)
            reflectiveCurveToRelative(7.4f, -13.62f, 10.76f, -19.05f)
            arcToRelative(30.38f, 30.38f, 0.0f, false, true, 8.22f, 4.83f)
            arcTo(40.48f, 40.48f, 0.0f, false, true, 363.85f, 370.65f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF454b69)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(772.41f, 606.74f)
            curveToRelative(-2.45f, 4.9f, -15.0f, 23.28f, -20.49f, 31.31f)
            lineTo(749.35f, 641.83f)
            reflectiveCurveToRelative(-1.0f, 6.64f, -17.39f, -1.59f)
            reflectiveCurveTo(330.48f, 469.52f, 330.48f, 469.52f)
            lineToRelative(-2.61f, -7.84f)
            lineToRelative(19.0f, -28.45f)
            lineTo(768.94f, 596.3f)
            reflectiveCurveTo(775.58f, 600.41f, 772.41f, 606.74f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(751.92f, 638.05f)
            lineTo(749.35f, 641.83f)
            reflectiveCurveToRelative(-1.0f, 6.64f, -17.39f, -1.59f)
            reflectiveCurveTo(330.48f, 469.52f, 330.48f, 469.52f)
            lineToRelative(-2.61f, -7.84f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFfbbebe)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(519.64f, 531.91f)
            curveToRelative(-3.91f, -0.64f, -8.25f, -0.33f, -11.29f, 2.21f)
            arcToRelative(9.29f, 9.29f, 0.0f, false, false, -3.24f, 6.16f)
            curveToRelative(-0.26f, 2.77f, 1.35f, 6.06f, 4.13f, 6.27f)
            arcToRelative(13.17f, 13.17f, 0.0f, false, false, 2.0f, -0.13f)
            arcToRelative(29.89f, 29.89f, 0.0f, false, true, 5.24f, 0.44f)
            arcToRelative(21.39f, 21.39f, 0.0f, false, false, 18.23f, -7.36f)
            curveToRelative(2.39f, -3.0f, -0.76f, -4.3f, -3.58f, -4.9f)
            curveTo(527.27f, 533.78f, 523.5f, 532.53f, 519.64f, 531.91f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(586.52f, 622.22f)
            lineToRelative(2.48f, 17.62f)
            arcToRelative(26.84f, 26.84f, 0.0f, false, true, 0.38f, 5.51f)
            curveToRelative(-0.29f, 4.15f, -2.5f, 7.87f, -4.09f, 11.71f)
            reflectiveCurveToRelative(-2.56f, 8.44f, -0.45f, 12.0f)
            curveToRelative(0.65f, 1.11f, 1.56f, 2.06f, 2.15f, 3.21f)
            arcToRelative(12.92f, 12.92f, 0.0f, false, true, 1.0f, 5.63f)
            lineToRelative(0.68f, 25.0f)
            arcToRelative(157.33f, 157.33f, 0.0f, false, false, 23.18f, -0.13f)
            arcToRelative(1.29f, 1.29f, 0.0f, false, false, 0.84f, -0.26f)
            arcToRelative(1.36f, 1.36f, 0.0f, false, false, 0.29f, -0.91f)
            curveToRelative(0.34f, -5.85f, -0.68f, -11.71f, -0.35f, -17.56f)
            curveToRelative(0.12f, -2.14f, 0.42f, -4.27f, 0.46f, -6.42f)
            curveToRelative(0.12f, -8.71f, -4.18f, -17.24f, -2.88f, -25.85f)
            arcToRelative(57.0f, 57.0f, 0.0f, false, true, 1.6f, -6.43f)
            curveToRelative(4.11f, -15.0f, 3.24f, -30.76f, 2.33f, -46.25f)
            arcToRelative(1.09f, 1.09f, 0.0f, false, false, -0.2f, -0.7f)
            arcToRelative(1.06f, 1.06f, 0.0f, false, false, -0.54f, -0.29f)
            arcToRelative(28.45f, 28.45f, 0.0f, false, false, -25.78f, 4.9f)
            curveToRelative(-1.09f, 0.88f, -3.84f, 1.73f, -4.56f, 2.78f)
            curveToRelative(-1.0f, 1.46f, 0.31f, 2.92f, 0.89f, 4.47f)
            arcTo(56.89f, 56.89f, 0.0f, false, true, 586.52f, 622.22f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(586.52f, 622.22f)
            lineToRelative(2.48f, 17.62f)
            arcToRelative(26.84f, 26.84f, 0.0f, false, true, 0.38f, 5.51f)
            curveToRelative(-0.29f, 4.15f, -2.5f, 7.87f, -4.09f, 11.71f)
            reflectiveCurveToRelative(-2.56f, 8.44f, -0.45f, 12.0f)
            curveToRelative(0.65f, 1.11f, 1.56f, 2.06f, 2.15f, 3.21f)
            arcToRelative(12.92f, 12.92f, 0.0f, false, true, 1.0f, 5.63f)
            lineToRelative(0.68f, 25.0f)
            arcToRelative(157.33f, 157.33f, 0.0f, false, false, 23.18f, -0.13f)
            arcToRelative(1.29f, 1.29f, 0.0f, false, false, 0.84f, -0.26f)
            arcToRelative(1.36f, 1.36f, 0.0f, false, false, 0.29f, -0.91f)
            curveToRelative(0.34f, -5.85f, -0.68f, -11.71f, -0.35f, -17.56f)
            curveToRelative(0.12f, -2.14f, 0.42f, -4.27f, 0.46f, -6.42f)
            curveToRelative(0.12f, -8.71f, -4.18f, -17.24f, -2.88f, -25.85f)
            arcToRelative(57.0f, 57.0f, 0.0f, false, true, 1.6f, -6.43f)
            curveToRelative(4.11f, -15.0f, 3.24f, -30.76f, 2.33f, -46.25f)
            arcToRelative(1.09f, 1.09f, 0.0f, false, false, -0.2f, -0.7f)
            arcToRelative(1.06f, 1.06f, 0.0f, false, false, -0.54f, -0.29f)
            arcToRelative(28.45f, 28.45f, 0.0f, false, false, -25.78f, 4.9f)
            curveToRelative(-1.09f, 0.88f, -3.84f, 1.73f, -4.56f, 2.78f)
            curveToRelative(-1.0f, 1.46f, 0.31f, 2.92f, 0.89f, 4.47f)
            arcTo(56.89f, 56.89f, 0.0f, false, true, 586.52f, 622.22f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(632.89f, 616.91f)
            curveToRelative(-1.17f, 2.43f, -2.74f, 4.69f, -3.44f, 7.3f)
            curveToRelative(-1.82f, 6.89f, 2.89f, 14.0f, 2.33f, 21.06f)
            curveToRelative(-0.18f, 2.23f, -0.87f, 4.4f, -0.92f, 6.63f)
            reflectiveCurveToRelative(0.77f, 4.71f, 2.75f, 5.74f)
            curveToRelative(1.33f, 0.68f, 3.0f, 0.61f, 4.25f, 1.39f)
            curveToRelative(2.11f, 1.29f, 2.35f, 4.22f, 2.14f, 6.68f)
            reflectiveCurveToRelative(-0.56f, 5.21f, 1.0f, 7.13f)
            curveToRelative(1.19f, 1.49f, 3.23f, 2.07f, 4.51f, 3.48f)
            arcToRelative(17.17f, 17.17f, 0.0f, false, true, 1.93f, 3.26f)
            curveToRelative(1.91f, 3.34f, 5.4f, 5.38f, 8.26f, 8.0f)
            reflectiveCurveToRelative(5.28f, 6.46f, 4.0f, 10.09f)
            curveToRelative(-0.94f, 2.66f, -3.58f, 4.3f, -6.12f, 5.55f)
            arcToRelative(64.93f, 64.93f, 0.0f, false, true, -14.57f, 5.15f)
            arcToRelative(3.52f, 3.52f, 0.0f, false, true, -1.67f, 0.09f)
            arcToRelative(3.57f, 3.57f, 0.0f, false, true, -1.46f, -1.0f)
            curveToRelative(-2.95f, -2.89f, -6.0f, -5.92f, -7.36f, -9.81f)
            arcToRelative(10.07f, 10.07f, 0.0f, false, false, -1.12f, -2.68f)
            curveToRelative(-1.56f, -2.19f, -4.85f, -2.11f, -7.07f, -3.62f)
            curveToRelative(-2.6f, -1.77f, -3.23f, -5.28f, -4.93f, -7.93f)
            reflectiveCurveToRelative(-4.19f, -4.23f, -6.0f, -6.56f)
            curveToRelative(-3.06f, -3.88f, -3.81f, -9.06f, -6.0f, -13.49f)
            curveToRelative(-0.93f, -1.88f, -2.12f, -3.64f, -2.86f, -5.6f)
            arcToRelative(30.58f, 30.58f, 0.0f, false, true, -1.37f, -7.24f)
            curveToRelative(-1.41f, -11.68f, -5.0f, -23.44f, -2.74f, -35.0f)
            curveToRelative(0.5f, -2.54f, 1.27f, -5.0f, 1.59f, -7.62f)
            curveToRelative(0.55f, -4.53f, -0.32f, -9.17f, 0.35f, -13.69f)
            arcToRelative(3.77f, 3.77f, 0.0f, false, true, 1.51f, -2.91f)
            arcToRelative(4.0f, 4.0f, 0.0f, false, true, 1.72f, -0.35f)
            arcToRelative(117.28f, 117.28f, 0.0f, false, true, 17.78f, 0.4f)
            curveToRelative(3.0f, 0.29f, 6.36f, 0.9f, 8.16f, 3.35f)
            curveToRelative(1.53f, 2.1f, 1.45f, 4.91f, 1.94f, 7.46f)
            curveToRelative(0.61f, 3.14f, 2.2f, 4.85f, 3.87f, 7.31f)
            reflectiveCurveTo(634.12f, 614.37f, 632.89f, 616.91f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF2f2e41)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(609.82f, 698.17f)
            curveToRelative(1.58f, 0.0f, 3.33f, -0.1f, 4.55f, 0.9f)
            curveToRelative(1.52f, 1.25f, 1.54f, 3.54f, 1.3f, 5.49f)
            curveToRelative(-0.7f, 5.48f, -2.49f, 10.75f, -4.28f, 16.0f)
            lineToRelative(-5.32f, 15.61f)
            arcToRelative(36.65f, 36.65f, 0.0f, false, false, -1.86f, 6.88f)
            curveToRelative(-0.52f, 4.0f, 0.28f, 8.0f, 0.38f, 12.0f)
            curveToRelative(0.0f, 2.0f, -0.13f, 4.22f, -1.38f, 5.83f)
            curveToRelative(-2.26f, 2.91f, -6.67f, 2.57f, -10.3f, 2.0f)
            lineToRelative(-17.71f, -3.0f)
            arcToRelative(21.07f, 21.07f, 0.0f, false, true, -4.38f, -1.07f)
            arcToRelative(30.61f, 30.61f, 0.0f, false, true, -4.25f, -2.39f)
            arcToRelative(65.42f, 65.42f, 0.0f, false, false, -11.0f, -5.0f)
            curveToRelative(-1.66f, -0.62f, -3.52f, -1.41f, -4.12f, -3.08f)
            curveToRelative(-0.71f, -2.0f, 0.87f, -4.2f, 2.83f, -5.0f)
            reflectiveCurveToRelative(4.15f, -0.6f, 6.27f, -0.54f)
            arcToRelative(47.0f, 47.0f, 0.0f, false, false, 15.4f, -2.08f)
            arcToRelative(9.74f, 9.74f, 0.0f, false, false, 4.06f, -2.1f)
            arcTo(10.0f, 10.0f, 0.0f, false, false, 582.35f, 733.91f)
            arcToRelative(70.17f, 70.17f, 0.0f, false, false, 2.68f, -16.0f)
            curveToRelative(0.21f, -4.0f, 0.07f, -8.0f, 0.23f, -12.0f)
            curveToRelative(0.1f, -2.37f, -0.13f, -10.0f, 1.95f, -11.63f)
            curveToRelative(1.59f, -1.22f, 8.13f, 2.15f, 10.28f, 2.64f)
            arcTo(50.0f, 50.0f, 0.0f, false, false, 609.82f, 698.17f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF2f2e41)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(668.16f, 709.29f)
            curveToRelative(1.73f, 5.73f, 2.0f, 11.79f, 3.12f, 17.68f)
            reflectiveCurveToRelative(3.1f, 11.9f, 7.47f, 16.0f)
            arcToRelative(9.87f, 9.87f, 0.0f, false, true, 2.17f, 2.4f)
            arcToRelative(5.06f, 5.06f, 0.0f, false, true, -0.75f, 5.23f)
            arcToRelative(13.13f, 13.13f, 0.0f, false, true, -4.39f, 3.37f)
            curveToRelative(-11.29f, 6.16f, -24.0f, 9.27f, -36.81f, 10.81f)
            arcToRelative(15.51f, 15.51f, 0.0f, false, true, -6.82f, -0.25f)
            curveToRelative(-2.18f, -0.74f, -4.11f, -2.7f, -4.07f, -5.0f)
            arcToRelative(2.55f, 2.55f, 0.0f, false, true, 0.6f, -1.73f)
            arcToRelative(3.73f, 3.73f, 0.0f, false, true, 1.35f, -0.79f)
            curveToRelative(4.72f, -1.94f, 9.51f, -4.05f, 13.16f, -7.63f)
            reflectiveCurveToRelative(5.94f, -9.0f, 4.53f, -13.86f)
            arcTo(27.42f, 27.42f, 0.0f, false, false, 644.35f, 729.11f)
            arcToRelative(108.35f, 108.35f, 0.0f, false, true, -5.0f, -9.69f)
            lineToRelative(-4.4f, -9.23f)
            curveToRelative(-1.29f, -2.7f, -2.59f, -5.48f, -2.81f, -8.46f)
            arcToRelative(40.6f, 40.6f, 0.0f, false, false, 16.22f, -2.6f)
            arcToRelative(25.51f, 25.51f, 0.0f, false, false, 6.67f, -4.0f)
            curveToRelative(1.7f, -1.42f, 3.25f, -4.07f, 5.54f, -2.22f)
            curveToRelative(1.84f, 1.48f, 3.0f, 5.65f, 4.08f, 7.76f)
            arcTo(57.0f, 57.0f, 0.0f, false, true, 668.16f, 709.29f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFfbbebe)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(603.68f, 480.1f)
            curveToRelative(0.44f, 3.84f, 4.16f, 6.37f, 7.71f, 7.89f)
            lineToRelative(-22.47f, 7.17f)
            curveToRelative(1.45f, -2.78f, 0.28f, -6.18f, -1.16f, -9.0f)
            arcToRelative(50.93f, 50.93f, 0.0f, false, false, -4.0f, -6.41f)
            curveToRelative(-0.22f, -0.31f, -0.47f, -0.7f, -0.31f, -1.06f)
            arcToRelative(1.12f, 1.12f, 0.0f, false, true, 0.63f, -0.49f)
            curveToRelative(4.62f, -2.06f, 9.38f, -4.19f, 14.43f, -4.94f)
            curveToRelative(1.46f, -0.21f, 4.22f, -0.79f, 5.31f, 0.69f)
            reflectiveCurveTo(603.47f, 478.33f, 603.68f, 480.1f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(603.68f, 480.1f)
            curveToRelative(0.44f, 3.84f, 4.16f, 6.37f, 7.71f, 7.89f)
            lineToRelative(-22.47f, 7.17f)
            curveToRelative(1.45f, -2.78f, 0.28f, -6.18f, -1.16f, -9.0f)
            arcToRelative(50.93f, 50.93f, 0.0f, false, false, -4.0f, -6.41f)
            curveToRelative(-0.22f, -0.31f, -0.47f, -0.7f, -0.31f, -1.06f)
            arcToRelative(1.12f, 1.12f, 0.0f, false, true, 0.63f, -0.49f)
            curveToRelative(4.62f, -2.06f, 9.38f, -4.19f, 14.43f, -4.94f)
            curveToRelative(1.46f, -0.21f, 4.22f, -0.79f, 5.31f, 0.69f)
            reflectiveCurveTo(603.47f, 478.33f, 603.68f, 480.1f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFFfbbebe)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(590.62f, 465.59f)
            moveToRelative(-17.62f, 0.0f)
            arcToRelative(17.62f, 17.62f, 0.0f, true, true, 35.24f, 0.0f)
            arcToRelative(17.62f, 17.62f, 0.0f, true, true, -35.24f, 0.0f)
        }
        path(
            fill = SolidColor(Color(0xFF2f2e41)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(575.35f, 610.59f)
            curveToRelative(1.71f, 2.05f, 4.72f, 2.45f, 7.35f, 2.0f)
            curveToRelative(6.15f, -1.0f, 11.18f, -5.53f, 17.2f, -7.16f)
            curveToRelative(7.38f, -2.0f, 15.19f, 0.57f, 22.16f, 3.72f)
            reflectiveCurveToRelative(13.9f, 7.0f, 21.52f, 7.54f)
            curveToRelative(-6.89f, -15.36f, -2.95f, -33.84f, -9.54f, -49.33f)
            curveToRelative(-1.61f, -3.79f, -3.86f, -7.44f, -4.22f, -11.54f)
            curveToRelative(-0.45f, -5.09f, 2.09f, -10.0f, 2.48f, -15.06f)
            curveToRelative(0.36f, -4.51f, -1.0f, -9.0f, -2.3f, -13.3f)
            quadToRelative(-2.41f, -7.92f, -4.82f, -15.84f)
            curveToRelative(-1.8f, -5.91f, -3.65f, -11.92f, -7.14f, -17.0f)
            curveToRelative(-1.29f, -1.87f, -1.68f, -5.66f, -3.64f, -6.79f)
            curveToRelative(-4.28f, -2.47f, -10.77f, 0.64f, -15.41f, 0.81f)
            reflectiveCurveToRelative(-8.87f, 3.47f, -11.58f, 7.2f)
            curveToRelative(-2.87f, 3.94f, -4.5f, 8.63f, -6.0f, 13.27f)
            arcToRelative(126.15f, 126.15f, 0.0f, false, false, -5.33f, 21.23f)
            curveToRelative(-0.15f, 1.08f, -0.28f, 2.16f, -0.38f, 3.25f)
            curveToRelative(-1.0f, 10.27f, 0.46f, 20.6f, 0.54f, 30.92f)
            verticalLineToRelative(0.07f)
            curveToRelative(0.1f, 13.09f, -2.0f, 26.1f, -2.39f, 39.18f)
            curveTo(573.81f, 606.15f, 573.87f, 608.76f, 575.35f, 610.59f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(576.27f, 564.52f)
            curveToRelative(3.45f, -0.87f, 6.57f, -3.1f, 9.25f, -5.54f)
            arcToRelative(38.67f, 38.67f, 0.0f, false, false, 6.14f, -7.0f)
            arcTo(49.86f, 49.86f, 0.0f, false, false, 596.95f, 540.91f)
            arcToRelative(129.36f, 129.36f, 0.0f, false, false, 7.0f, -32.27f)
            curveToRelative(0.26f, -3.0f, 0.34f, -6.22f, -1.45f, -8.6f)
            arcToRelative(7.24f, 7.24f, 0.0f, false, false, -8.75f, -2.12f)
            curveToRelative(-2.36f, 1.15f, -3.89f, 3.48f, -5.31f, 5.71f)
            curveToRelative(-2.81f, 4.42f, -5.64f, 8.9f, -7.45f, 13.82f)
            curveToRelative(-0.8f, 2.18f, -1.39f, 4.42f, -2.17f, 6.61f)
            arcToRelative(61.0f, 61.0f, 0.0f, false, true, -2.74f, 6.33f)
            curveToRelative(-0.15f, 1.08f, -0.28f, 2.16f, -0.38f, 3.25f)
            curveTo(574.77f, 543.91f, 576.19f, 554.2f, 576.27f, 564.52f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF2f2e41)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(602.45f, 498.11f)
            curveToRelative(1.79f, 2.38f, 1.71f, 5.63f, 1.46f, 8.59f)
            arcToRelative(129.18f, 129.18f, 0.0f, false, true, -7.0f, 32.27f)
            arcToRelative(49.43f, 49.43f, 0.0f, false, true, -5.29f, 11.16f)
            arcToRelative(39.07f, 39.07f, 0.0f, false, true, -6.14f, 7.0f)
            curveToRelative(-3.4f, 3.1f, -7.54f, 5.87f, -12.15f, 5.91f)
            arcToRelative(23.16f, 23.16f, 0.0f, false, true, -7.56f, -1.65f)
            lineTo(528.22f, 548.61f)
            curveToRelative(-0.38f, -2.14f, 1.13f, -4.64f, 1.84f, -6.7f)
            lineToRelative(2.0f, -5.93f)
            curveToRelative(0.57f, -1.64f, 1.23f, -3.41f, 2.72f, -4.3f)
            reflectiveCurveToRelative(3.85f, 0.33f, 5.09f, 1.32f)
            reflectiveCurveToRelative(2.18f, 2.29f, 3.4f, 3.3f)
            curveToRelative(2.31f, 1.92f, 5.36f, 2.64f, 8.3f, 3.3f)
            lineToRelative(8.43f, 1.89f)
            arcToRelative(35.2f, 35.2f, 0.0f, false, false, 9.38f, 1.25f)
            curveToRelative(2.35f, -7.18f, 6.82f, -13.5f, 9.35f, -20.62f)
            curveToRelative(0.78f, -2.18f, 1.37f, -4.43f, 2.18f, -6.61f)
            curveToRelative(1.8f, -4.92f, 4.64f, -9.39f, 7.45f, -13.82f)
            curveToRelative(1.41f, -2.22f, 2.94f, -4.55f, 5.31f, -5.71f)
            arcTo(7.23f, 7.23f, 0.0f, false, true, 602.45f, 498.11f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF2f2e41)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(596.24f, 469.57f)
            arcToRelative(8.89f, 8.89f, 0.0f, false, false, -1.06f, -3.3f)
            arcToRelative(2.77f, 2.77f, 0.0f, false, false, -3.0f, -1.32f)
            curveToRelative(-1.76f, 0.55f, -2.35f, 3.27f, -4.19f, 3.36f)
            curveToRelative(-1.24f, 0.06f, -2.13f, -1.2f, -2.52f, -2.38f)
            reflectiveCurveToRelative(-0.6f, -2.53f, -1.53f, -3.36f)
            arcToRelative(5.7f, 5.7f, 0.0f, false, false, -2.18f, -1.0f)
            curveToRelative(-4.27f, -1.35f, -8.68f, -3.84f, -10.1f, -8.09f)
            arcToRelative(4.84f, 4.84f, 0.0f, false, true, -0.23f, -2.71f)
            curveToRelative(0.65f, -2.69f, 4.0f, -3.5f, 6.55f, -4.47f)
            reflectiveCurveToRelative(5.0f, -2.63f, 7.5f, -3.89f)
            reflectiveCurveToRelative(5.4f, -2.15f, 8.15f, -1.51f)
            arcToRelative(42.47f, 42.47f, 0.0f, false, true, 4.11f, 1.53f)
            curveToRelative(4.14f, 1.44f, 9.21f, 0.77f, 12.42f, 3.76f)
            arcToRelative(12.83f, 12.83f, 0.0f, false, true, 3.0f, 5.24f)
            curveToRelative(2.24f, 6.22f, 3.66f, 13.6f, -0.05f, 19.06f)
            curveToRelative(-2.29f, 3.37f, -4.51f, 7.22f, -8.91f, 7.63f)
            curveTo(599.08f, 478.55f, 597.05f, 473.91f, 596.24f, 469.57f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(588.42f, 463.81f)
            curveToRelative(-0.39f, -1.18f, -0.6f, -2.53f, -1.52f, -3.36f)
            arcToRelative(5.7f, 5.7f, 0.0f, false, false, -2.18f, -1.0f)
            curveToRelative(-4.28f, -1.35f, -8.68f, -3.84f, -10.11f, -8.09f)
            arcToRelative(4.93f, 4.93f, 0.0f, false, true, -0.22f, -2.71f)
            arcToRelative(3.39f, 3.39f, 0.0f, false, true, 0.53f, -1.19f)
            curveToRelative(-1.63f, 0.71f, -3.05f, 1.65f, -3.45f, 3.31f)
            arcToRelative(4.84f, 4.84f, 0.0f, false, false, 0.23f, 2.71f)
            curveToRelative(1.42f, 4.25f, 5.83f, 6.74f, 10.1f, 8.09f)
            arcToRelative(5.7f, 5.7f, 0.0f, false, true, 2.18f, 1.0f)
            curveToRelative(0.93f, 0.83f, 1.13f, 2.17f, 1.53f, 3.36f)
            reflectiveCurveToRelative(1.28f, 2.44f, 2.52f, 2.38f)
            reflectiveCurveToRelative(1.84f, -1.19f, 2.63f, -2.14f)
            curveTo(589.57f, 466.03f, 588.78f, 464.91f, 588.42f, 463.81f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(607.14f, 475.97f)
            curveToRelative(-5.14f, 0.47f, -7.17f, -4.21f, -8.0f, -8.52f)
            arcToRelative(8.7f, 8.7f, 0.0f, false, false, -1.0f, -3.3f)
            arcToRelative(2.78f, 2.78f, 0.0f, false, false, -3.0f, -1.32f)
            arcToRelative(5.32f, 5.32f, 0.0f, false, false, -2.21f, 2.0f)
            arcToRelative(3.0f, 3.0f, 0.0f, false, true, 2.26f, 1.4f)
            arcToRelative(8.89f, 8.89f, 0.0f, false, true, 1.06f, 3.3f)
            curveToRelative(0.81f, 4.31f, 2.84f, 9.0f, 8.0f, 8.52f)
            arcToRelative(8.25f, 8.25f, 0.0f, false, false, 5.28f, -2.74f)
            arcTo(7.46f, 7.46f, 0.0f, false, true, 607.14f, 475.97f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(780.82f, 655.78f)
            arcToRelative(32.29f, 6.21f, 0.0f, true, false, 64.58f, 0.0f)
            arcToRelative(32.29f, 6.21f, 0.0f, true, false, -64.58f, 0.0f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(808.81f, 653.14f)
            arcToRelative(3.76f, 4.92f, 0.0f, true, false, 7.52f, 0.0f)
            arcToRelative(3.76f, 4.92f, 0.0f, true, false, -7.52f, 0.0f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(808.81f, 647.12f)
            arcToRelative(3.76f, 4.92f, 0.0f, true, false, 7.52f, 0.0f)
            arcToRelative(3.76f, 4.92f, 0.0f, true, false, -7.52f, 0.0f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(808.81f, 641.11f)
            arcToRelative(3.76f, 4.92f, 0.0f, true, false, 7.52f, 0.0f)
            arcToRelative(3.76f, 4.92f, 0.0f, true, false, -7.52f, 0.0f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(808.81f, 635.09f)
            arcToRelative(3.76f, 4.92f, 0.0f, true, false, 7.52f, 0.0f)
            arcToRelative(3.76f, 4.92f, 0.0f, true, false, -7.52f, 0.0f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(808.81f, 629.07f)
            arcToRelative(3.76f, 4.92f, 0.0f, true, false, 7.52f, 0.0f)
            arcToRelative(3.76f, 4.92f, 0.0f, true, false, -7.52f, 0.0f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(808.81f, 623.06f)
            arcToRelative(3.76f, 4.92f, 0.0f, true, false, 7.52f, 0.0f)
            arcToRelative(3.76f, 4.92f, 0.0f, true, false, -7.52f, 0.0f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(808.81f, 617.04f)
            arcToRelative(3.76f, 4.92f, 0.0f, true, false, 7.52f, 0.0f)
            arcToRelative(3.76f, 4.92f, 0.0f, true, false, -7.52f, 0.0f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(826.64f, 575.91f)
            arcToRelative(18.19f, 18.19f, 0.0f, false, false, 1.4f, -2.06f)
            lineToRelative(-9.88f, -1.62f)
            lineToRelative(10.69f, 0.07f)
            arcToRelative(18.11f, 18.11f, 0.0f, false, false, 0.34f, -14.27f)
            lineToRelative(-14.34f, 7.44f)
            lineToRelative(13.22f, -9.72f)
            arcTo(18.0f, 18.0f, 0.0f, true, false, 798.35f, 575.91f)
            arcToRelative(17.92f, 17.92f, 0.0f, false, false, -2.06f, 3.28f)
            lineToRelative(12.83f, 6.67f)
            lineToRelative(-13.68f, -4.59f)
            arcTo(18.0f, 18.0f, 0.0f, false, false, 798.35f, 598.15f)
            arcToRelative(18.0f, 18.0f, 0.0f, true, false, 28.31f, 0.0f)
            arcToRelative(18.0f, 18.0f, 0.0f, false, false, 0.0f, -22.27f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(794.47f, 587.01f)
            arcTo(17.91f, 17.91f, 0.0f, false, false, 798.35f, 598.15f)
            arcToRelative(18.0f, 18.0f, 0.0f, true, false, 28.31f, 0.0f)
            curveTo(829.05f, 595.08f, 794.47f, 584.99f, 794.47f, 587.01f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(101.93f, 776.87f)
            arcToRelative(46.49f, 8.94f, 0.0f, true, false, 92.98f, 0.0f)
            arcToRelative(46.49f, 8.94f, 0.0f, true, false, -92.98f, 0.0f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(142.23f, 773.07f)
            arcToRelative(5.41f, 7.09f, 0.0f, true, false, 10.82f, 0.0f)
            arcToRelative(5.41f, 7.09f, 0.0f, true, false, -10.82f, 0.0f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(142.23f, 764.41f)
            arcToRelative(5.41f, 7.09f, 0.0f, true, false, 10.82f, 0.0f)
            arcToRelative(5.41f, 7.09f, 0.0f, true, false, -10.82f, 0.0f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(142.23f, 755.75f)
            arcToRelative(5.41f, 7.09f, 0.0f, true, false, 10.82f, 0.0f)
            arcToRelative(5.41f, 7.09f, 0.0f, true, false, -10.82f, 0.0f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(142.23f, 747.09f)
            arcToRelative(5.41f, 7.09f, 0.0f, true, false, 10.82f, 0.0f)
            arcToRelative(5.41f, 7.09f, 0.0f, true, false, -10.82f, 0.0f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(142.23f, 738.43f)
            arcToRelative(5.41f, 7.09f, 0.0f, true, false, 10.82f, 0.0f)
            arcToRelative(5.41f, 7.09f, 0.0f, true, false, -10.82f, 0.0f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(142.23f, 729.76f)
            arcToRelative(5.41f, 7.09f, 0.0f, true, false, 10.82f, 0.0f)
            arcToRelative(5.41f, 7.09f, 0.0f, true, false, -10.82f, 0.0f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF3f3d56)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(142.23f, 721.1f)
            arcToRelative(5.41f, 7.09f, 0.0f, true, false, 10.82f, 0.0f)
            arcToRelative(5.41f, 7.09f, 0.0f, true, false, -10.82f, 0.0f)
            close()
        }
        path(
            fill = SolidColor(primaryColor), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(167.9f, 661.84f)
            arcToRelative(27.23f, 27.23f, 0.0f, false, false, 2.0f, -3.0f)
            lineToRelative(-14.22f, -2.34f)
            lineToRelative(15.38f, 0.12f)
            arcToRelative(26.0f, 26.0f, 0.0f, false, false, 0.49f, -20.55f)
            lineTo(150.93f, 646.81f)
            lineToRelative(19.0f, -14.0f)
            arcToRelative(25.93f, 25.93f, 0.0f, true, false, -42.83f, 29.0f)
            arcToRelative(25.83f, 25.83f, 0.0f, false, false, -3.0f, 4.72f)
            lineToRelative(18.47f, 9.6f)
            lineToRelative(-19.69f, -6.61f)
            arcTo(25.93f, 25.93f, 0.0f, false, false, 127.14f, 693.91f)
            arcToRelative(25.93f, 25.93f, 0.0f, true, false, 40.76f, 0.0f)
            arcToRelative(25.92f, 25.92f, 0.0f, false, false, 0.0f, -32.06f)
            close()
        }
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.1f, strokeAlpha
            = 0.1f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
            strokeLineMiter = 4.0f, pathFillType = NonZero
        ) {
            moveTo(121.59f, 677.91f)
            arcToRelative(25.85f, 25.85f, 0.0f, false, false, 5.55f, 16.0f)
            arcToRelative(25.93f, 25.93f, 0.0f, true, false, 40.76f, 0.0f)
            curveTo(171.35f, 689.49f, 121.59f, 674.96f, 121.59f, 677.91f)
            close()
        }
    }
        .build()
}

