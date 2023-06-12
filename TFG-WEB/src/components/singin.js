import React, { useState, useEffect } from 'react';
import "./initStyle.css"
import "./boton.scss"
import icono from './icon.png'
import { Button } from "@mui/material"
import { FaLock, FaUser, FaMailBulk, FaArrowRight } from "react-icons/fa";
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import InputAdornment from '@mui/material/InputAdornment';
// https://sweetalert2.github.io/#frameworks-integrations
import Swal from 'sweetalert2'

export function SingIn() {

    const apiUrl = process.env.REACT_APP_API_BASE_URL;
    const [protonmail, setProtonmail] = useState("");
    const [password, setPassword] = useState("");
    const [nick, setNick] = useState("");
    const fotPerfil = "https://i.pinimg.com/280x280_RS/66/a1/a4/66a1a4a22e271ec3ee3ef35ba0883ca4.jpg";
    const [error, setError] = useState(false);
    const [errorMensaje, setErrorMensaje] = useState(""); 

    const data = `{
        "nmro_id": 0,
        "txto_protonmail": "${protonmail}",
        "txto_psswrd": "${password}",
        "foto_perfil": "${fotPerfil}",
        "txto_nick": "${nick}",
        "cdgo_rango": 0
    }`;

    function crearUsuario() {
        console.log(data);
        fetch(`${apiUrl}/setUsers`, {
            method: "POST",
            headers: {
                'accept': ' application/json',
                'Content-Type': ' application/json'
            },
            body: data,
        })
            .then((response) => response.json())
            .then(data =>
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: 'La solicitud ha sido enviada',
                    showConfirmButton: false,
                    timer: 2000
                }),
                setError(false),
                setErrorMensaje(""),
                setProtonmail(""),
                setPassword(""),
                setNick(""))
            .catch((error) => Swal.fire({
                position: 'center',
                icon: 'error',
                title: "ERROR 404",
                text: 'Ha ocurrido un error inesperado',
                showConfirmButton: false,
                timer: 2000
            }));
    }

    const checkProtonMail = (email) => {
        const protoCheck = /@protonmail\.com$/i;
        return protoCheck.test(email);
    };

    function compProtonmail() {
        console.log(data);
        if(!checkProtonMail(protonmail)){
            Swal.fire({
                position: 'center',
                icon: 'warning',
                title: "Eso no es un protonmail",
                showConfirmButton: false,
                timer: 2000
            })
        }else{
            fetch(`${apiUrl}/getUser/protonmail/${protonmail}`, {
                headers: {
                    'Content-Type': ' application/json'
                }
            })
                .then((response) => response.json())
                .then(data => console.log(data),
                    setError(true),
                    setErrorMensaje("Escribe un protonmail correcto")
                )
                .catch((error) => crearUsuario());
        }
        
    }

    function handleSubmit() {
        compProtonmail();
    }

    return (
        <>
            <div className="main">
                <p className="paginas" ><a className="noActual" href="/">Home</a> | <a className="noActual" href="/about-us">About us</a> | <a className="actual" href="/sing-in">Sign in</a></p>
            </div>
            <div className='tarjeta f1'>
                <div class="form">
                    <form className="formularioTarjeta" >
                        <div class="input-container ic1">
                            <h3>SIGN IN</h3>
                            <Box
                                component="form"
                                sx={{
                                    '& .MuiTextField-root': { m: 1.5, width: '25ch' },
                                }}
                                noValidate
                                autoComplete="off"
                            >
                                <Grid item xs={15}>
                                    <TextField InputProps={{
                                        startAdornment: (
                                            <InputAdornment position="start">
                                                <FaUser />
                                            </InputAdornment>
                                        ),
                                    }} id="standard-search" required label="Nombre de usuario" type="text" variant="standard" value={nick} onChange={(e) => setNick(e.target.value)} />
                                </Grid>
                                <Grid item xs={15}>
                                    <TextField InputProps={{
                                        startAdornment: (
                                            <InputAdornment position="start">
                                                <FaMailBulk />
                                            </InputAdornment>
                                        ),
                                    }} id="standard-search" required label="Protonmail" type="text" variant="standard" error={error} helperText={errorMensaje} value={protonmail} onChange={(e) => setProtonmail(e.target.value)} />
                                </Grid>
                                <Grid item xs={15}>
                                    <TextField InputProps={{
                                        startAdornment: (
                                            <InputAdornment position="start">
                                                <FaLock />
                                            </InputAdornment>
                                        ),
                                    }} id="standard-search" required label="Contraseña" type="password" variant="standard" value={password} onChange={(e) => setPassword(e.target.value)} />
                                </Grid>
                                <br></br>
                                <br></br>
                                <Button className='boton' variant="contained" onClick={() => handleSubmit()}>
                                    UNIRSE
                                </Button>
                            </Box>
                        </div>
                    </form>
                </div>
            </div>
        </>

    )
}