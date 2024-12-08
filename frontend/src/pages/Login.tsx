import { useState } from "react";
import '../styles/form.css'
import {URL} from "../config/api.js";

const LoginContainer = () => {
    const [loginData, setLoginData] = useState({
        email: '',
        password: '',
    })

    /*const submit = (e) => {
        e.preventDefault();
        console.log('submit', loginData)
        // logika za login ide ovdje
    }*/
    const submit = async (e) => {
        e.preventDefault();  // Prevent default form submission behavior

        try {
            // Assume loginData contains user input for email and password
            const response = await fetch(`${URL}/api/auth/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',  // Sending JSON data
                },
                body: JSON.stringify({
                    email: loginData.email,  // Replace with the actual state or value for email
                    password: loginData.password  // Replace with the actual state or value for password
                })
            });

            if (!response.ok) {  // Check if the response is not OK (status code 2xx)
                throw new Error('Login failed! Check your credentials and try again.');
            }

            // Parse the response as JSON
            const data = await response.json();

            console.log('Login successful!', data);

            // Example: Save JWT token in localStorage or context, and redirect user
            localStorage.setItem('token', data.token);  // Assuming the response contains a token
            // Redirect to dashboard or another page
            //window.location.href = '/dashboard';  // Replace with your desired redirect

        } catch (error) {
            // Handle errors such as network issues or failed login
            console.error('Login error:', error.message);
            // Optional: Show error message to the user in the UI
        }
    };


    const change = (e) => {
        setLoginData({
            ...loginData,
            [e.target.name]: e.target.value
        })
    }

    return (
        <div id='loginContainer'>
            <p>Login</p>

            <form onSubmit={submit}>
                <input
                    type="email"
                    name="email"
                    placeholder="Email"
                    onChange={change}
                    value={loginData.email} />
                <input
                    type="password"
                    name="password"
                    placeholder="Password"
                    onChange={change}
                    value={loginData.password} />
                <button type="submit">Login</button>
            </form>
        </div>
    )
}

export default LoginContainer;
