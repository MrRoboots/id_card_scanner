#import "IdCardScannerPlugin.h"
#if __has_include(<id_card_scanner/id_card_scanner-Swift.h>)
#import <id_card_scanner/id_card_scanner-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "id_card_scanner-Swift.h"
#endif

@implementation IdCardScannerPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftIdCardScannerPlugin registerWithRegistrar:registrar];
}
@end
