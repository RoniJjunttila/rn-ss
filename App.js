import React, { useEffect } from 'react';
import { NativeModules, Button, View, Text } from 'react-native';

const App = () => {
  const takeScreenshot = () => {
    NativeModules.MediaProjectionModule.startScreenCapture().then(
      (result) => {
        console.log(result); // Handle success
      }
    ).catch((error) => {
        console.error(error); // Handle error
    });
  };

  return (
    <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
      <Button title="Take Screenshot" onPress={takeScreenshot} />
    </View>
  );
};

export default App;
