import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:scanner/id_card_controller.dart';
import 'package:scanner/id_card_view.dart';
import 'package:scanner/scanner_method_channel.dart';
import 'package:permission_handler/permission_handler.dart';

void main() {
  runApp(MaterialApp(home: MyApp()));
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  MethodChannelScanner methodChannelScanner = MethodChannelScanner();

  @override
  void initState() {
    super.initState();
    Permission.camera.request();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Column(
          children: [
            ElevatedButton(
              onPressed: () {
                methodChannelScanner.openIdCardScanner();
              },
            ),
            ElevatedButton(
              onPressed: () {
                Navigator.of(context).push(MaterialPageRoute(
                  builder: (context) => const ScannerExample(),
                ));
              },
              child: const Text('qrView'),
            ),
          ],
        ));
  }
}

class ScannerExample extends StatefulWidget {
  const ScannerExample({Key key}) : super(key: key);

  @override
  State<ScannerExample> createState() => _ScannerExampleState();
}

class _ScannerExampleState extends State<ScannerExample> {
  // IDCardController controller;
  final GlobalKey qrKey = GlobalKey(debugLabel: 'QR');

  @override
  void initState() {
    super.initState();
  }

  @override
  void dispose() {
    super.dispose();
  /*  if (controller != null) {
      controller.closeCream();
    }*/
  }

  void idCardController(IDCardController controller) {
    // controller.openCream();
    // setState(() {
    //   this.controller = controller;
    // });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("12312312")),
      body: Stack(
        children: [
          IDCardScannerView(key: qrKey, callback: idCardController),
          Container(
            color: Colors.red,
            width: 200,
            height: 200,
          )
        ],
      ),
    );

    /* return Stack(
      children: [
        SizedBox(
          width: 200,
          height: 200,
          child: IDCardScannerView(key: qrKey, callback: idCardController),
        ),
        */ /*   Container(
          decoration: ShapeDecoration(
            shape: QrScannerOverlayShape(),
          ),
        ),*/ /*
      ],
    );*/
  }
}
