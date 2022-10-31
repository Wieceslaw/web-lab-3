function setTimezoneCookie () {
    const timezone_cookie = "timezoneoffset";
    let offset = new Date().getTimezoneOffset();
    if (!Cookies.get(timezone_cookie)) {
        Cookies.set(timezone_cookie, offset, { expires: 30, path: '/' });
        location.reload();
    }
    else {
        let storedOffset = parseInt(Cookies.get(timezone_cookie));
        if (storedOffset !== offset) {
            Cookies.set(timezone_cookie, offset, { expires: 30, path: '/' });
            location.reload();
        }
    }
}

setTimezoneCookie()
changeGraphLabels()
