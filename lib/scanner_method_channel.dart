import 'package:flutter/services.dart';

class MethodChannelScanner {
  final methodChannel = const MethodChannel('scanner');

  Future<String> openIdCardScanner() async {
    final version = await methodChannel.invokeMethod<String>('openIdCardScanner');
    return version;
  }
}
