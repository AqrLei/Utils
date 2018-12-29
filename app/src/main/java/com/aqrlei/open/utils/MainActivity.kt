package com.aqrlei.open.utils


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.aqrlei.open.utils.qrcode.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        qrCodeTest()
    }

    private fun qrCodeTest() {
        qrcodeCl.visibility = View.VISIBLE
        val qrCode = QRCode.Builder()
            .addContext(this)
            .addLogoAdapterFactory(RoundLogoAdapter.Factory())
            .addContainerConfigure(QRContainer.ContainerConfigure(width = 288F, height = 353F))
            .addQRConfigure(QRContent.QRConfigure(width = 192F, height = 192F, topMargin = 83F))
            .addTextConfigure(
                QRText.TextConfigure(topOrBottomMargin = 33F),
                QRText.TextConfigure(topOrBottomMargin = 30F)
            )
            .build()

        button.setOnClickListener {
            qrCode?.run {
                wipeAll()
                refreshContainer(QRContainer.ContainerConfigure(width = 288F, height = 353F))
                refreshContent(QRContent.QRConfigure(topMargin = 83F))
                refreshTopTextConfig(QRText.TextConfigure(topOrBottomMargin = 33F))
                refreshBottomTextConfig(QRText.TextConfigure(topOrBottomMargin = 30F))
                addLogoCreator {
                    getBitmapFromRes()
                }
                drawQRContent("https://www.baidu.com/")
                drawText(topContent = "这就是一个测试", bottomContent = "这是一个测试啊")
                qrCodeIv.setImageBitmap(get())
            }
        }
        refreshQrCode.setOnClickListener {
            qrCode?.run {
                wipeAll()
                refreshContainer(QRContainer.ContainerConfigure(width = 288F, height = 315F))
                refreshContent(QRContent.QRConfigure(topMargin = 43F))
                refreshTopTextConfig(null)
                refreshBottomTextConfig(QRText.TextConfigure(topOrBottomMargin = 33F))
                addLogoCreator {
                    getBitmapFromRes()
                }
                drawQRContent("https://www.baidu.com/")
                drawText(topContent = "这就是一个测试", bottomContent = "这是一个测试啊")
                qrCodeIv.setImageBitmap(get())
            }

        }
    }

    private fun getBitmapFromRes(): Bitmap? {
        return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            val drawable = getDrawable(R.mipmap.ic_launcher)

            drawable?.let {
                val bitmap = Bitmap.createBitmap(
                    drawable.intrinsicWidth,
                    drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(bitmap)
                drawable.setBounds(0, 0, canvas.width, canvas.height)
                drawable.draw(canvas)
                bitmap
            }
        } else {
            BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
        }
    }

}
