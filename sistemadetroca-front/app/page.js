"use client";
import { useState } from 'react';
import Image from "next/image";
import HendleSignup from "components/handleSignup"
import { useRouter } from 'next/navigation';
// import { cookies } from 'next/headers';
import Cookies from 'js-cookie';

export default function Login() {

  const [login, setUsername] = useState('');
  const [senha, setPassword] = useState('');
  const router = useRouter();

  const handleLogin = async () => {
    try {
      const response = await fetch('http://127.0.0.1:8080/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ login, senha }),
      });
  
      if (response.ok) {
        const data = await response.json();
        const userId = data;
        Cookies.set("UserId", userId)
        alert(data)
        console.log('Login concluído! ID do usuário:', userId);
        router.push('/home');
      } else {
        console.error('Falha no usuário ou senha:', response.statusText);
      }
    } catch (error) {
      console.error('Erro ao processar login:', error);
    }
  };

  return (
    <main className="flex h-dvh justify-center items-center">
      <div className="flex items-center w-[70rem] h-[40rem] rounded-2xl shadow-lg">

        <div className="flex bg-[#F26329] w-[25rem] h-full  justify-center rounded-s-2xl hover:w-[80rem] transition-all duration-500">
          <div className="w-full">
            <div className="flex flex-col justify-center items-center h-[30rem] gap-4">
              <div className="flex justify-center items-center font-bold h-[10rem]">
                <span className="text-white text-[35px]">Seja bem vindo!</span>
              </div>
              <div className="flex w-[50%] p-3 bg-slate-100 rounded-sm gap-1">
                <Image src="/icons/mail_black.svg" alt="User Icon" width={25} height={25} />
                <input value={login} onChange={(e) => setUsername(e.target.value)} className="w-[90%] bg-slate-100 focus:outline-none" placeholder="Email"></input>
              </div>
              <div className="flex w-[50%] p-3 bg-slate-100 rounded-sm gap-1">
                <Image src="/icons/lock_black.svg" alt="User Icon" width={25} height={25} />
                <input type="password" value={senha} onChange={(e) => setPassword(e.target.value)} className="w-[90%] bg-slate-100 focus:outline-none" placeholder="Confirmar Senha"></input>
              </div>
              <div className="flex w-[50%] justify-center">
                <button onClick={handleLogin} className="p-2 w-[8rem] rounded-lg text-white border border-white">Login</button>
              </div>
            </div>
          </div>
        </div>

        <HendleSignup />

      </div>
    </main>
  );
}

