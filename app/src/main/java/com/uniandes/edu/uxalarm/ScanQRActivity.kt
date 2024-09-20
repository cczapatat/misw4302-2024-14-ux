package com.uniandes.edu.uxalarm

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.SurfaceHolder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.zxing.Result
import com.uniandes.edu.uxalarm.databinding.ActivityScanQrBinding
import me.dm7.barcodescanner.zxing.ZXingScannerView


class ScanQRActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private lateinit var binding: ActivityScanQrBinding
    private lateinit var scannerView: ZXingScannerView
    private val CAMERA_PERMISSION_REQUEST_CODE = 200


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanQrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        scannerView = ZXingScannerView(this)
        val cameraPreview = binding.cameraPreview
        cameraPreview.holder.addCallback(object: SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                if (checkCameraPermission()) {
                    println("entro aca")
                    startCamera()
                } else {
                    requestCameraPermission()
                }
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                stopCamera()
            }
        })

        binding.btnCloseScanQr.setOnClickListener {
            finish()
        }
    }

    private fun startCamera() {
        scannerView.setResultHandler(this)
        scannerView.startCamera()
    }

    private fun stopCamera() {
        scannerView.stopCamera()

    }

    private fun checkCameraPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                print("entro acaaaaa 2")
                startCamera()
            } else {
                Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun handleResult(p0: Result?) {
        p0?.let {
            Toast.makeText(this, "Resultado: ${it.text}", Toast.LENGTH_LONG).show()

            // Reiniciar el escaneo
            scannerView.resumeCameraPreview(this)
        }
    }

    override fun onPause() {
        super.onPause()
        stopCamera()
    }

    override fun onResume() {
        super.onResume()
        if (checkCameraPermission()) {
            startCamera()
        }
    }
}