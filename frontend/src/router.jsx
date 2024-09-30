import React from "react";
import { createBrowserRouter } from 'react-router-dom'

import HomeContainer from "./containers/Home";
import LoginContainer from "./containers/Login";
import RegisterContainer from "./containers/Register";
import HotelsContainer from "./containers/Hotels";

const router = createBrowserRouter([
    {
        path: "",
        element: <div>Hello world!</div>,
    },
    {
        path: "/home",
        element: <div>Test</div>
    },
    {
        path: '/hotels',
        element: <HotelsContainer />
    },
    {
        path: '/flights',
        element: <div>Flights</div>
    },
    {
        path: '/destinations',
        element: <div>Destinations</div>
    },
    {
        path: '/login',
        element: <LoginContainer />
    },
    {
        path: '/register',
        element: <RegisterContainer />
    }
])

export default router;