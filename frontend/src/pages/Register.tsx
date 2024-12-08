import { useState } from "react";
import '../styles/form.css'
import { URL } from "../config/api.js";

const RegisterContainer = () => {
    const [registerData, setRegisterData] = useState({
        email: '',
        password: '',
        confirmPassword: '',
        firstName: '',
        lastName: '',
        username: ''
    })

    const submit = async (e) => {
        e.preventDefault();

        try {
            if (registerData.password === registerData.confirmPassword) {
                console.log('submit', registerData)
                const res = await fetch(`${URL}/api/auth/register`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',  // Set the Content-Type to JSON
                    },
                    body: JSON.stringify({
                        userType: 'USER',
                        firstName: registerData.firstName,
                        lastName: registerData.lastName,
                        email: registerData.email,
                        password: registerData.password
                    })
                })

                console.log(res)
            }
        } catch (error) {
            console.log(error)
        }
    }

    const change = (e) => {
        setRegisterData({
            ...registerData,
            [e.target.name]: e.target.value
        })
    }

    return (
        <div id='loginContainer'>
            <p>Register</p>

            <form onSubmit={submit}>
                <input
                    type="email"
                    name="email"
                    placeholder="Email"
                    onChange={change}
                    value={registerData.email} />
                <input
                    type="password"
                    name="password"
                    placeholder="Password"
                    onChange={change}
                    value={registerData.password} />
                <input
                    type="password"
                    name="confirmPassword"
                    placeholder="Confirm password"
                    onChange={change}
                    value={registerData.confirmPassword} />
                <input
                    type="text"
                    name="firstName"
                    placeholder="First name"
                    onChange={change}
                    value={registerData.firstName} />
                <input
                    type="text"
                    name="lastName"
                    placeholder="Last name"
                    onChange={change}
                    value={registerData.lastName} />
                <input
                    type="text"
                    name="username"
                    placeholder="Username"
                    onChange={change}
                    value={registerData.username} />
                <button type="submit">Register</button>
            </form>
        </div>
    )
}

export default RegisterContainer;
