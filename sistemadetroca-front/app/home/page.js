
import Image from "next/image";
import Navbar from '@components/navbar';

export default function HomePage() {
    return (
        <div className="flex flex-col h-dvh bg-slate-100">
            <Navbar />
            <div className="flex flex-col w-[20%] h-full bg-white shadow-lg gap-5 p-2">
                <div className="flex flex-col gap-2">
                    <span className="font-bold">Categorias</span>

                    <div className="flex gap-1 ml-2">
                        <div className="bg-slate-400 rounded-full p-1 hover:bg-[#F26329]">
                            <Image src={"/book_black.svg"} width={25} height={25} />
                        </div>
                        <span className="flex items-center">Livros</span>
                    </div>
                </div>
            </div>
        </div>
    );
};