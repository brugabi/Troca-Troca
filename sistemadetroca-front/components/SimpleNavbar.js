// components/SimpleNavbar.js
import Image from "next/image";
import Link from 'next/link';

const SimpleNavbar = () => {
    return (
        <div className="flex items-center w-full h-[4rem] shadow-lg border-2 border-opacity-1 px-[3rem] bg-white">
            <div className="flex justify-start items-center w-[50%]">
                <Image src="/image/troca-logo.png" alt="Logo" width={120} height={64} />
            </div>
            <div className="flex w-[50%] justify-end">
                <nav className="flex gap-4">
                    <Link href="/home" className="highlighted-links hover:underline">Home</Link>
                    <Link href="http://localhost:3000/home/proposals" className="highlighted-links hover:underline">Propostas</Link>
                    <Link href="http://localhost:3000/home/myposts" className="highlighted-links hover:underline">Meus anuncios</Link>
                </nav>
            </div>
        </div>
    );
};

export default SimpleNavbar;
