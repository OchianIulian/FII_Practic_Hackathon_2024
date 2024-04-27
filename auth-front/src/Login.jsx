import React from "react";

function Login() {
  const handleSubmit = (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    const username = formData.get("username");
    const password = formData.get("password");
    // Trimite datele la server sau efectuează operațiile de autentificare
  };

  return (
    <div className="container">
      <form className="form-signin" onSubmit={handleSubmit}>
        <h2 className="form-signin-heading">Please sign in</h2>
        <p>
          <label htmlFor="username" className="sr-only">
            Username
          </label>
          <input
            type="text"
            id="username"
            name="username"
            className="form-control"
            placeholder="Username"
            required
            autoFocus
          />
        </p>
        <p>
          <label htmlFor="password" className="sr-only">
            Password
          </label>
          <input
            type="password"
            id="password"
            name="password"
            className="form-control"
            placeholder="Password"
            required
          />
        </p>
        <button className="btn btn-lg btn-primary btn-block" type="submit">
          Sign in
        </button>
      </form>

      <h2 className="form-signin-heading">Login with OAuth 2.0</h2>
      <table className="table table-striped">
        <tbody>
          <tr>
            <td>
              <a href="http://localhost:8080/oauth2/authorization/github">
                GitHub
              </a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
}

export default Login;
