import Image from 'next/image';
import Link from 'next/link';

const NavBar = () => {
    return (
        <div className="flex items-center w-full h-[4rem] shadow-lg border-2 border-opacity-1 px-[3rem] bg-white">
            <div className="flex justify-start items-center w-[33%]">
                <Link href="/home">
                    <Image src="/image/troca-logo.png" alt="Logo" width={120} height={64} />
                </Link>
            </div>
            <div className="flex border rounded-xl p-1 w-[33%]">
                <input className="w-full focus:outline-none" placeholder="Pesquisar..."/>
                <Image src="/icons/search.svg" alt="User Icon" width={25} height={25} />
            </div>
            <div className="flex w-[33%] justify-end">
                <nav className="flex gap-4">
                    <Link href="/home/newpost">
                        <span className="highlighted-links hover:underline">Criar troca</span>
                    </Link>
                    <Link href="/home/proposals">
                        <span className="highlighted-links hover:underline">Propostas</span>
                    </Link>
                </nav>
            </div>
        </div>
    );
};

export default NavBar;
