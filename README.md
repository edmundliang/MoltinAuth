# Moltin API Authentication 
This is an Android app that interacts with the Moltin API. It displays whether or not authentication with the Moltin API was successful.
## Tutorial
###Create a Moltin Object
```
Moltin moltin = new Moltin(this);
```
###Authentication
Get the public client id key from the API console on Moltin's website and set it to a final variable.

```
private static final String client_id = "your_public_key";
```
I created a button that when clicked, calls the authenticateMoltinAPI method and passes the client id string as a parameter.

```
mButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        authenticateMoltinAPI(client_id);
    }
});
```
Inside the method, the Moltin object calls the authenticate method with the public key that was passed as a parameter and a new Handler callback object. The callback object lets you know if authentication as successful or not. The authenticate method in the Moltin Android SDK throws an excpetion if the public key string is an empty string, so it must be surrounded by a try-catch block. The Handler returns a message and msg.what is called to obtain the message code. If the authentication was successful, the message code is 1 and is compared to CONSTANTS.RESULT_OK which is initialized to 1. If the authentication was not successful, the message code would be 0. A message is then displayed on the screen showing the status.

```
public void authenticateMoltinAPI(String id) {
    try {
        moltin.authenticate(id, new Handler.Callback() {
           @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == Constants.RESULT_OK) {
                    Toast.makeText(getApplicationContext(), "Authentication Successful.", Toast.LENGTH_LONG).show();
                    return true;
                } else {
                   Toast.makeText(getApplicationContext(), "Authentication Failed. Try Again.", Toast.LENGTH_LONG).show();
                   return false;
                }
            }
        });
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

