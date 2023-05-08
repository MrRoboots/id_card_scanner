
import 'dart:async';

import 'package:flutter/services.dart';

class IdCardScanner {
  static const MethodChannel _channel =
      const MethodChannel('id_card_scanner');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
