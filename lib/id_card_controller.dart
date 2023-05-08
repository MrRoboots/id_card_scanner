import 'package:flutter/services.dart';

import 'constant.dart';

class IDCardController {
  final MethodChannel idCardChannel;

  IDCardController.withId(int id) : idCardChannel = MethodChannel('$CHANNEL_NAME/id_card_status$id');

  ///打开相机
  Future<bool> openCream() async {
    return await idCardChannel.invokeMethod("openCream");
  }

  ///停止相机
  Future<bool> closeCream() async {
    return await idCardChannel.invokeMethod("closeCream");
  }
}
