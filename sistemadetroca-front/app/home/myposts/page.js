

export default function MyPost() {
    const [items, setItems] = useState([
        {
            id: 1,
            image: "https://via.placeholder.com/400x200",
            title: "Livro Frontend",
            description: "Livro de frontend seminovo...",
        },
        {
            id: 2,
            image: "https://via.placeholder.com/400x200",
            title: "Console de Jogos",
            description: "Console em ótimo estado...",
        },
        {
            id: 3,
            image: "https://via.placeholder.com/400x200",
            title: "Acessório de Computador",
            description: "Mouse gamer com iluminação RGB...",
        },
    ]);

    const deleteItem = (id) => {
        setItems(items.filter(item => item.id !== id));
    };

    return (
        <div className="flex flex-col min-h-screen bg-slate-100">
            <Navbar />
            <div className="flex flex-col items-center flex-1 overflow-hidden p-4">
                <div className="bg-white shadow-lg rounded-md p-6 w-full max-w-4xl">
                    <h1 className="text-2xl font-bold mb-4">Inventário do Usuário</h1>
                    <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                        {items.map(item => (
                            <div key={item.id} className="bg-white shadow-md rounded-md overflow-hidden">
                                <Image src={item.image} width={400} height={200} className="w-full h-auto" alt={item.title} />
                                <div className="p-4">
                                    <h2 className="text-lg font-bold">{item.title}</h2>
                                    <p className="text-gray-600">{item.description}</p>
                                    <button
                                        className="mt-2 px-4 py-2 bg-red-500 text-white rounded-md shadow-md hover:bg-red-600"
                                        onClick={() => deleteItem(item.id)}
                                    >
                                        Deletar
                                    </button>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    );
}
