class Auth {
    constructor() {
        this.user_token = localStorage.getItem("jwt") || {};
    }
    getToken() {
        return this.user_token;
    }
    setUserToken(new_token) {
        this.user_token = new_token;
        localStorage.setItem("jwt", JSON.stringify(new_token));
    }
    logout() {
        localStorage.removeItem("jwt");
    }
}
const auth = new Auth();
export default auth;