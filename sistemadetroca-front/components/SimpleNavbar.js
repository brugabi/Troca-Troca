// components/SimpleNavbar.js
import Image from "next/image";
import Link from 'next/link';

const SimpleNavbar = () => {
    return (
        <div className="flex items-center w-full h-[4rem] shadow-lg border-2 border-opacity-1 px-[3rem] bg-white">
            <div className="flex justify-start items-center w-[50%]">
            <Link href="/home">
                    <Image src="/image/troca-logo.png" alt="Logo" width={120} height={64} />
                </Link>
            </div>
            <div className="flex w-[50%] justify-end">
                <nav className="flex gap-4">
                <Link href="/home/newpost">
                        <span className="highlighted-links hover:underline">Criar troca</span>
                    </Link>
                    <Link href="/home/proposals">
                        <span className="highlighted-links hover:underline">Propostas recebidas</span>
                    </Link>
                    <Link href="/home/myproposals">
                        <span className="highlighted-links hover:underline">Propostas enviadas</span>
                    </Link>
                    <Link 
                        href="http://localhost:3000/home/myposts" className="highlighted-links hover:underline">Meus anuncios
                    </Link>
                </nav>
            </div>
        </div>
    );
};

export default SimpleNavbar;
