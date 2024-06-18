
export default function Home() {
  return (
    <main className="flex justify-center items-center h-dvh bg-slate-950 ">
      <div className="flex flex-col justify-center items-center bg-slate-500 h-[70%] w-[40%] gap-2 rounded-md">
        <div className="flex flex-col w-[50%]">
          <span>Nome</span>
          <input className="pl-1 rounded-md text-slate-700" placeholder="digite..."/>
        </div>
        <div className="flex flex-col w-[50%]">
          <span>Senha</span>
          <input className="pl-1 rounded-md text-slate-700" placeholder="digite..."/>
        </div>

        <div className="flex gap-2">
          <div><button className="bg-green-400 p-2 w-[6rem] rounded-md">Login</button></div>
          <div><button className="bg-green-400 p-2 w-[6rem] rounded-md">Cadastrar</button></div>
        </div>
      </div>
    </main>
  );
}
