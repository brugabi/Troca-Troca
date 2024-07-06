import Image from 'next/image';
import Link from 'next/link';

const SampleCard = () => {
    const post = {
        id: 1,
        image: "https://via.placeholder.com/400x200",
        title: "Livro Frontend",
        description: "livro de frontend seminovo ...",
        userAvatar: "https://via.placeholder.com/30",
        username: "@usuario1",
    };

    return (
        <div className="bg-white shadow-md rounded-md overflow-hidden mb-4 w-96">
            <Image src={post.image} width={400} height={200} className="w-full h-auto" alt={post.title} />
            <div className="p-4">
                <h2 className="text-lg font-bold">{post.title}</h2>
                <p className="text-gray-600">{post.description}</p>
                <Link href="#" className="text-blue-600 hover:underline">Ver detalhes</Link>
                <div className="flex items-center mt-2">
                    <Image src={post.userAvatar} width={30} height={30} className="rounded-full" alt={post.username} />
                    <span className="ml-2 text-gray-600">{post.username}</span>
                </div>
            </div>
        </div>
    );
};

export default SampleCard;
