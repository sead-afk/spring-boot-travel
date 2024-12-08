import {Route, Routes} from 'react-router-dom';
import { Hotels, Home, Login, Register } from "./pages";

function App() {
  return (
      <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/home" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/hotels" element={<Hotels />} />
          <Route path="register" element={<Register />} />
          { /*<Route path="*" element={<NotFound/>}/> */}
      </Routes>
  )
}

export default App

