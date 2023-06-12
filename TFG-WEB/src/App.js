import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Init } from './components/inicial';
import { Aboutus } from './components/aboutus';
import { SingIn } from './components/singin';

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Init/>}/>
          <Route path="/about-us" element={<Aboutus/>}/>
          <Route path="/sing-in" element={<SingIn/>}/>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
