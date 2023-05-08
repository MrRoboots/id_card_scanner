import 'dart:io';
import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'constant.dart';
import 'id_card_controller.dart';

const String viewType = '$CHANNEL_NAME/IDCardScanner';

typedef CreatedCallback = void Function(IDCardController controller);

class IDCardScannerView extends StatelessWidget {
  const IDCardScannerView({Key key, this.callback}) : super(key: key);
  final CreatedCallback callback;

  @override
  Widget build(BuildContext context) {
    final gestureRecognizers = {
      Factory<OneSequenceGestureRecognizer>(
        () => EagerGestureRecognizer(),
      ),
    };

    if (Platform.isAndroid) {
      return AndroidView(
        viewType: viewType,
        hitTestBehavior: PlatformViewHitTestBehavior.opaque,
        gestureRecognizers: gestureRecognizers,
        onPlatformViewCreated: _onViewCreated,
      );
    } else {
      return const Text('插件未支持的平台');
    }
  }

  void _onViewCreated(int id) {
    final controller = IDCardController.withId(id);
    if (callback != null) {
      callback(controller);
    }
  }
}
