import Image from "next/image";

export default function Home() {
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
                <Image src="/mail_black.svg" alt="User Icon" width={25} height={25} />
                <input type="email" className="w-[90%] bg-slate-100 focus:outline-none" placeholder="Email"></input>
              </div>
              <div className="flex w-[50%] p-3 bg-slate-100 rounded-sm gap-1">
                <Image src="/lock_black.svg" alt="User Icon" width={25} height={25} />
                <input type="password" className="w-[90%] bg-slate-100 focus:outline-none" placeholder="Confirmar Senha"></input>
              </div>
              <div className="flex w-[50%] justify-center">
                <button className="p-2 w-[8rem] rounded-lg text-white border border-white">Login</button>
              </div>
            </div>
          </div>
        </div>

        <div className="w-[50rem] rounded-e-2xl">
          <div className="flex flex-col justify-center items-center h-[30rem] gap-4">
            <div className="flex flex-col justify-center items-center h-[10rem]">
              <span className="text-[#F26329] font-bold text-[35px]">Crie uma conta</span>
              <span className="text-slate-400">Insira as informações solicitadas abaixo</span>
            </div>
            <div className="flex w-[50%] p-3 bg-slate-100 gap-1">
              <Image src="/user.svg" alt="User Icon" width={25} height={25} />
              <input className="w-[90%] bg-slate-100 focus:outline-none" placeholder="Usuário"></input>
            </div>
            <div className="flex w-[50%] p-3 bg-slate-100 gap-1">
              <Image src="/mail.svg" alt="User Icon" width={25} height={25} />
              <input type="email" className="w-[90%] bg-slate-100 focus:outline-none" placeholder="Email"></input>
            </div>
            <div className="flex w-[50%] p-3 bg-slate-100 gap-1">
              <Image src="/lock.svg" alt="User Icon" width={25} height={25} />
              <input type="password" className="w-[90%] bg-slate-100 focus:outline-none" placeholder="Senha"></input>
            </div>
            <div className="flex w-[50%] p-3 bg-slate-100 gap-1">
              <Image src="/lock.svg" alt="User Icon" width={25} height={25} />
              <input type="password" className="w-[90%] bg-slate-100 focus:outline-none" placeholder="Confirmar Senha"></input>
            </div>
            <div className="flex w-[50%] justify-center">
              <button className="bg-[#F26329] p-2 rounded-lg text-white">Criar Conta</button>
            </div>
          </div>
        </div>

      </div>
    </main>
  );
}
