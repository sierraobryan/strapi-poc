To run this project, you will also need to be running the [Strapi Backend](https://github.com/sierraobryan/strapi-poc) locally. Directions are provided to get the backend up and running. 

After following the backend directions, you will need to add your android app to the same Firebase project and include the config file (google-services.json) to the project. This should be all you need to use basic email + password firebase authentication. You can then run locally on an emulator. 

This project also includes sign in with google. To use sign in with google, you will need to add your SHA-1 to your firebase project, enable sign in with google in the firebase console, and get your server's client ID from GCP. 

Directions for enabling sign in with google can her found [here](https://firebase.google.com/docs/auth/android/google-signin)

## License
```
Copyright 2020 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
