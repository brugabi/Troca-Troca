import Image from "next/image";
import Navbar from '@components/navbar';

const posts = [
    {
        id: 1,
        image: "/path/to/image.jpg",
        title: "Livro Frontend",
        description: "livro de frontend seminovo ...",
        userAvatar: "/path/to/avatar.jpg",
        username: "@usuario1",
    },
    {
        id: 2,
        image: "/path/to/image.jpg",
        title: "Título do item 2",
        description: "Descrição curta do item 2...",
        userAvatar: "/path/to/avatar.jpg",
        username: "@usuario2",
    },
    {
        id: 3,
        image: "/path/to/image.jpg",
        title: "Título do item 3",
        description: "Descrição curta do item 3...",
        userAvatar: "/path/to/avatar.jpg",
        username: "@usuario3",
    },
    {
        id: 4,
        image: "/path/to/image.jpg",
        title: "Título do item 4",
        description: "Descrição curta do item 4...",
        userAvatar: "/path/to/avatar.jpg",
        username: "@usuario4",
    },
    {
        id: 5,
        image: "/path/to/image.jpg",
        title: "Título do item 5",
        description: "Descrição curta do item 5...",
        userAvatar: "/path/to/avatar.jpg",
        username: "@usuario5",
    },
    {
        id: 6,
        image: "/path/to/image.jpg",
        title: "Título do item 6",
        description: "Descrição curta do item 6...",
        userAvatar: "/path/to/avatar.jpg",
        username: "@usuario6",
    },
    {
        id: 7,
        image: "/path/to/image.jpg",
        title: "Título do item 7",
        description: "Descrição curta do item 7...",
        userAvatar: "/path/to/avatar.jpg",
        username: "@usuario7",
    },
    {
        id: 8,
        image: "/path/to/image.jpg",
        title: "Título do item 8",
        description: "Descrição curta do item 8...",
        userAvatar: "/path/to/avatar.jpg",
        username: "@usuario8",
    },
    {
        id: 9,
        image: "/path/to/image.jpg",
        title: "Título do item 9",
        description: "Descrição curta do item 9...",
        userAvatar: "/path/to/avatar.jpg",
        username: "@usuario9",
    },
];

export default function HomePage() {
    return (
        <div className="flex flex-col h-dvh bg-slate-100">
            <Navbar />
            <div className="flex flex-row h-full">
                <div className="flex flex-col w-[20%] bg-white shadow-lg gap-5 p-2">
                    <div className="flex flex-col gap-2">
                        <span className="font-bold">Categorias</span>

                        <div className="flex gap-1 ml-2">
                            <div className="bg-slate-400 rounded-full p-1 hover:bg-[#F26329]">
                                <Image src={"/book_black.svg"} width={25} height={25} />
                            </div>
                            <span className="flex items-center">Livros</span>
                        </div>

                        <div className="flex gap-1 ml-2">
                            <div className="bg-slate-400 rounded-full p-1 hover:bg-[#F26329]">
                                <Image src={"/game_black.svg"} width={25} height={25} />
                            </div>
                            <span className="flex items-center">Jogos</span>
                        </div>

                        <div className="flex gap-1 ml-2">
                            <div className="bg-slate-400 rounded-full p-1 hover:bg-[#F26329]">
                                <Image src={"/game_black.svg"} width={25} height={25} />
                            </div>
                            <span className="flex items-center">Acessórios</span>
                        </div>
                    </div>
                </div>

                <div className="flex flex-col w-[80%] p-4" style={{ height: '634px', overflowY: 'scroll' }}>
                    <button className="mb-4 bg-blue-500 text-white py-2 px-4 rounded">Adicionar Novo Post</button>
                    <div className="grid grid-cols-2 md:grid-cols-3 gap-4">
                        {posts.map(post => (
                            <div key={post.id} className="bg-white shadow-md rounded-md overflow-hidden">
                                <Image src={post.image} width={400} height={200} className="w-full h-auto" />
                                <div className="p-4">
                                    <h2 className="text-lg font-bold">{post.title}</h2>
                                    <p className="text-gray-600">{post.description}</p>
                                    <a href="#" className="text-blue-600 hover:underline">Ver detalhes</a>
                                    <div className="flex items-center mt-2">
                                        <Image src={post.userAvatar} width={30} height={30} className="rounded-full" />
                                        <span className="ml-2 text-gray-600">{post.username}</span>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    );
};
