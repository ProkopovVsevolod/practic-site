import React, { useState } from 'react'
import axios from 'axios';
import validator from 'validator';
import { DOMEN_SERVER, DOMEN_SITE } from '../../config/const';
import './Register.css'

export default function Register () {
    const [register, setRegister] = useState(() => {
        return {
            username: "",
            email: "",
            password: "",
            password2: "",
        }
    })
     
    const changeInputRegister = event => {
        event.persist()
        setRegister(prev => {
            return {
                ...prev,
                [event.target.name]: event.target.value,
            }
        })
    }
     
     
    const submitChackin = event => {
        event.preventDefault();
        if(!validator.isEmail(register.email)) {
            alert("You did not enter email")
        } else if(register.password !== register.password2) {
            alert("Repeated password incorrectly")
        } else if(!validator.isStrongPassword(register.password, {minSymbols: 0})) {
            alert("Password must consist of one lowercase, uppercase letter and number, at least 8 characters")
        } else {
            axios.post(DOMEN_SERVER + "/auth/registration/", {
                username: register.username,
                email: register.email,
                password: register.password,
            }).then(res => {
                if (res.data === true) {
                    window.location.href = DOMEN_SITE + "/auth"
                } else {
                    alert("There is already a user with this email")
                }
            }).catch(() => {
                alert("An error occurred on the server")
            })
        }
    }
    return (
        <div className="form">
            <h2>Register user:</h2>
            <form onSubmit={submitChackin}>
            <p>Name: <input className="input" type="username" name= "username" id = "username" size="40" value={register.username}
                onChange={changeInputRegister}/>
            </p>
            <p>Email: <input className="input" type="email" name = "email" id = "email" size="40" value={register.email}
                onChange={changeInputRegister}
            formnovalidate/>   
            </p>
            <p>Password: <input className="input" type="password" name = "password" id = "password" size="40" value={register.password}
                onChange={changeInputRegister}/></p>
            <p>Repeat password: <input className="input" type="password2" name = "password2" id = "password2" size="40" value={register.password2}
                onChange={changeInputRegister}/></p>
            <input className='button' type="submit"/>
            </form>
        </div>
    )
}