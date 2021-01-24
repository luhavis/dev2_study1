function onSignIn(googleUser) {


    var id_token = googleUser.getAuthResponse().id_token
    googleUser.disconnect()
    var xhr = new XMLHttpRequest()
    xhr.open("POST", "/auth/googlesign");
    xhr.setRequestHeader("Content-Type", "application/json")
    xhr.onload = function() {
        const data = JSON.parse(xhr.response)

        const form = document.createElement("form")
        form.setAttribute("method", "get")
        form.setAttribute("action", data.redirectURI)

        for (let i in data) {
            let input = document.createElement("input")
            input.setAttribute("type", "hidden")
            input.setAttribute("name", i)
            input.setAttribute("value", data[i])
            form.appendChild(input)
        }


        document.getElementsByTagName("body")[0].appendChild(form)

        form.submit()

    }
    xhr.send(JSON.stringify({
        id_token: id_token
    }))
}

function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.')
    });
}