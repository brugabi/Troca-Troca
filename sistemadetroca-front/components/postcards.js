import { useState, useEffect } from 'react';
import Link from 'next/link';
import Cookies from 'js-cookie';
import Image from 'next/image'; // Importando o componente Image do Next.js

const PostCards = () => {
    const [screenHeight, setScreenHeight] = useState(0);
    const [posts, setPosts] = useState([]);
    const userId = Cookies.get('UserId');

    useEffect(() => {
        const fetchPosts = async () => {
            try {
                const response = await fetch('http://127.0.0.1:8080/api/anuncios/listarAnuncios');
                const data = await response.json();
                console.log('UserId:', userId); 
                console.log('Data:', data.Lista); 
                
                const filteredPosts = data.Lista.filter(post => String(post.criador.id) !== String(userId) && post.status === true);
                setPosts(filteredPosts);
                console.log('Filtered Posts:', filteredPosts);
            } catch (error) {
                console.error('Erro ao buscar os posts:', error);
            }
        };

        fetchPosts();

        const updateHeight = () => {
            setScreenHeight(window.innerHeight);
        };

        updateHeight();
        window.addEventListener('resize', updateHeight);

        return () => window.removeEventListener('resize', updateHeight);
    }, [userId]);

    const handleViewMoreClick = (id) => {
        localStorage.setItem('selectedPostId', id);
    };

    return (
        <div className="flex flex-col w-full p-4" style={{ height: `calc(${screenHeight}px - 64px)`, overflowY: 'scroll' }}>
            <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
                {posts.map(post => (
                    <div key={post.id} className="bg-white shadow-md rounded-md overflow-hidden">
                        {post.imagemUrl && (
                            <Image 
                                src={`http://127.0.0.1:8080/${post.imagemUrl}`} 
                                alt={post.titulo} 
                                className="w-full h-48 object-contain"
                                width={500} 
                                height={300} 
                            />
                        )}
                        <div className="p-4 items-start">
                            <div className="flex justify-start">
                                <span className="text-gray-600">@{post.criador.login}</span>
                            </div>
                            <h2 className="text-lg font-bold">{post.titulo}</h2>
                            <p className="text-gray-600 text-">{post.descricao}</p>
                            <div className="flex flex-row justify-between items-center mt-2">
                                <Link href="/home/viewmore" className="text-blue-600 hover:underline" onClick={() => handleViewMoreClick(post.id)}>Ver detalhes</Link>
                                <div>
                                    <p className="text-gray-600">{post.departamento.nome}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default PostCards;
