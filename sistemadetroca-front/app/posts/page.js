import Navbar from '@components/navbar';

export default function NewPost(){
    return(
        <div className="flex flex-col min-h-screen bg-slate-100">
            <Navbar />

            <div className='flex justify-center w-full h-svh'>
                <div className='flex justify-end w-[70%] h-full bg-white shadow'>
                    <div>

                    </div>
                    <div className='w-[30%] h-full bg-slate-400'>
                        <span>Titulo do ba</span>
                    </div>
                </div>
            </div>
        </div>
    );
}