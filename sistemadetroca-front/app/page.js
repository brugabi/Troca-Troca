
import Image from "next/image";
import HendleSignup from "components/handleSignup"

export default function Login() {

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
                <input type="email" className="w-[90%] bg-slate-100 focus:outline-none" placeholder="Email"></input>
              </div>
              <div className="flex w-[50%] p-3 bg-slate-100 rounded-sm gap-1">
                <Image src="/icons/lock_black.svg" alt="User Icon" width={25} height={25} />
                <input type="password" className="w-[90%] bg-slate-100 focus:outline-none" placeholder="Confirmar Senha"></input>
              </div>
              <div className="flex w-[50%] justify-center">
                <button className="p-2 w-[8rem] rounded-lg text-white border border-white">Login</button>
              </div>
            </div>
          </div>
        </div>

        <HendleSignup/>

      </div>
    </main>
  );
}

