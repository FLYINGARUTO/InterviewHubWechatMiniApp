import config from "../config/config"

// Wrapper function for uni.request
const request = (option) => {
    return new Promise((resolve, reject) => {
        // Make an HTTP request using uni.request
        uni.request({
            url: config.apiBaseUrl + option.url, // Combine base URL with endpoint
            data: option.data || {}, // Use provided data or an empty object
            header: option.header || {}, // Use provided headers or an empty object
            method: option.method || "GET", // Default HTTP method is GET

            // Callback for successful request
            success: (res) => {
                // Check if the response code indicates success
                if (res.data.code == 20000) {
                    resolve(res); // Resolve the promise with the response
                } else {
                    // Handle business logic errors
                    let errInfo = res.data?.message || "unknown error"; // Default to 'unknown error' if no message is provided
                    uni.showToast({
                        icon: "error", // Display an error icon
                        duration: 3000, // Show the toast for 3 seconds
                        title: errInfo // Show the error message
                    });
                    setTimeout(() => {
                        reject(new Error(res.statusCode)); // Reject the promise after a delay
                    }, 3000);
                }
            },

            // Callback for failed request
            fail: (err) => {
                let errInfo = '';
                // Handle specific error scenarios
                if (err.errMsg.includes('timeout')) {
                    errInfo = 'request timeout, please try again';
                } else if (err.errMsg.includes('abort')) {
                    errInfo = 'request data wrong, please try again';
                } else {
                    errInfo = 'Internet request wrong';
                }
                
                // Show a toast notification with the error
                uni.showToast({
                    icon: "none", // Display a neutral icon
                    title: errInfo, // Show the error message
                    duration: 3000 // Show the toast for 3 seconds
                });
                reject(new Error(errInfo)); // Reject the promise with the error message
            }
        });
    });
};

// Export the request function as the default export
export default request;
