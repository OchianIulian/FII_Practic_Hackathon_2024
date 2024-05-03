import capsules from "./capsulesmock";

//mock API call
export const fetchCapsules = () => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve(capsules);
    }, 2500);
  });
};

export default fetchCapsules;
